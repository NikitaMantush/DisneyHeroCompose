package com.mantushnikita.disneyherocompose.ui.favourite.list

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mantushnikita.disneyherocompose.R
import com.mantushnikita.disneyherocompose.navigation.FAVOURITE_SCREEN
import com.mantushnikita.disneyherocompose.navigation.LIST_SCREEN
import com.mantushnikita.disneyherocompose.navigation.LOGIN_SCREEN
import com.mantushnikita.disneyherocompose.navigation.SINGUP_SCREEN
import com.mantushnikita.disneyherocompose.ui.theme.Purple80
import com.mantushnikita.disneyherocompose.util.custom.IconWithText
import com.mantushnikita.disneyherocompose.util.list.SetList

@Composable
fun FavouriteListScreen(
    navigationController: NavHostController,
    viewModel: FavouriteListViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.processAction(FavouriteListAction.Init)

    }
    var currentUserEmail = Firebase.auth.currentUser?.email

    Image(
        painter = painterResource(id = R.drawable.ic_cloud), contentDescription = null,
        contentScale = ContentScale.Fit
    )
    Column {
        Row {
            Button(onClick = { navigationController.navigate(
                LIST_SCREEN
            ) }) {
                Text(text = "All")
            }
            if (currentUserEmail != null) {
                Button(onClick = {
                    Firebase.auth.signOut()
                    currentUserEmail = null
                }) {
                    Text(text = "Logout")
                }
            } else {
                Button(onClick = { navigationController.navigate(LOGIN_SCREEN) }) {
                    Text(text = "Login")
                }
            }

            Button(colors = ButtonDefaults.buttonColors(Color.Red), onClick = {
                navigationController.navigate(
                    SINGUP_SCREEN
                )
            }) {
                Text(text = "SingUp")
            }
            Text(
                text = currentUserEmail ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterVertically),
                textAlign = TextAlign.End,
                fontSize = 12.sp,
                color = Color.White
            )
        }
        Row(modifier = Modifier.padding(top = 30.dp)) {
            IconWithText(
                iconResourceId = R.drawable.ic_all,
                text = "All"
            )
            IconWithText(
                iconResourceId = R.drawable.ic_favorite,
                text = "Favorite"
            )
        }
        FavouriteScreenListState(state = viewModel.state, navigationController = navigationController)
    }
}

@Composable
fun FavouriteScreenListState(
    state: MutableLiveData<FavouriteListState>,
    navigationController: NavHostController
) {
    state.observeAsState().value?.let {
        when (it) {
            is FavouriteListState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(30.dp),
                        color = Purple80
                    )
                }
            }
            is FavouriteListState.Loaded -> {
                SetList(
                    list = it.heroList,
                    navigationController = navigationController
                )
            }
            is FavouriteListState.Error -> {
                Toast.makeText(LocalContext.current, it.error, Toast.LENGTH_LONG).show()
            }
        }
    }
}
