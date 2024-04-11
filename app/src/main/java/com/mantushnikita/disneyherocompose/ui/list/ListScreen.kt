package com.mantushnikita.disneyherocompose.ui.list

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import com.mantushnikita.disneyherocompose.navigation.LOGIN_SCREEN
import com.mantushnikita.disneyherocompose.navigation.SINGUP_SCREEN
import com.mantushnikita.disneyherocompose.ui.theme.Purple80
import com.mantushnikita.disneyherocompose.util.custom.IconWithText
import com.mantushnikita.disneyherocompose.util.list.SetList

@Composable
fun ListScreen(
    navigationController: NavHostController,
    viewModel: ListViewModel = hiltViewModel()
) {
    viewModel.processAction(ListScreenAction.Init)
    var currentUserEmail = Firebase.auth.currentUser?.email

    Image(
        painter = painterResource(id = R.drawable.ic_cloud), contentDescription = null,
        contentScale = ContentScale.Fit
    )
    Column {
        Row {
            Button( onClick = { navigationController.navigate(
                FAVOURITE_SCREEN) }) {
                Text(text = "Favourite")
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

            Button(colors = ButtonDefaults.buttonColors(Color.Red), onClick = { navigationController.navigate(SINGUP_SCREEN) }) {
                Text(text = "SingUp")
            }
            Text(
                text = currentUserEmail ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = CenterVertically),
                textAlign = TextAlign.End,
                fontSize = 12.sp,
                color = Color.White
            )
        }
        Row(modifier = Modifier.padding(top = 15.dp)) {
            IconWithText(iconResourceId = R.drawable.ic_all, text = "All")
            IconWithText(iconResourceId = R.drawable.ic_favorite, text = "Favorite")
        }
        ListScreenState(state = viewModel.state, navigationController = navigationController)
    }
}

@Composable
fun ListScreenState(
    state: MutableLiveData<ListScreenState>,
    navigationController: NavHostController
) {
    state.observeAsState().value?.let {
        when (it) {
            is ListScreenState.Loading -> {
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

            is ListScreenState.Loaded -> {
                SetList(list = it.heroList, navigationController = navigationController)
            }

            is ListScreenState.Error -> {
                Toast.makeText(LocalContext.current, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}


