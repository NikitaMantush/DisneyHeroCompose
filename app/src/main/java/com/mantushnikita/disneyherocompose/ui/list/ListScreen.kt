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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
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
import coil.compose.AsyncImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mantushnikita.disneyherocompose.R
import com.mantushnikita.disneyherocompose.model.Hero
import com.mantushnikita.disneyherocompose.navigation.HERO_SCREEN
import com.mantushnikita.disneyherocompose.navigation.LOGIN_SCREEN
import com.mantushnikita.disneyherocompose.navigation.SINGUP_SCREEN
import com.mantushnikita.disneyherocompose.ui.theme.Purple80

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
            Button(onClick = { navigationController.navigate(SINGUP_SCREEN) }) {
                Text(text = "SingUp")
            }
            Text(
                text = currentUserEmail ?: "",
                modifier = Modifier.fillMaxWidth().align(alignment = CenterVertically),
                textAlign = TextAlign.End,
                fontSize = 12.sp,
                color = Color.White
            )
        }
        Row(modifier = Modifier.padding(top = 30.dp)) {
            IconWithText(iconResourceId = R.drawable.ic_all, text = "All")
            IconWithText(iconResourceId = R.drawable.ic_favorite, text = "Favorite")
        }
        listScreenState(state = viewModel.state, navigationController = navigationController)
    }
}

@Composable
fun listScreenState(
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
                setList(list = it.heroList, navigationController = navigationController)
            }

            is ListScreenState.Error -> {
                Toast.makeText(LocalContext.current, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun setList(list: List<Hero>, navigationController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        items(count = list.size ?: 0) { index ->
            val hero = list.getOrNull(index)
            if (hero != null) {
                HeroItem(hero = hero) { id ->
                    navigationController.navigate("$HERO_SCREEN/$id")
                }
            }
        }
    }
}

@Composable
private fun HeroItem(hero: Hero, onClick: (idHero: Int) -> Unit) {
    Column(
        modifier = Modifier.selectable(
            selected = true,
            onClick = { onClick(hero._id) }
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = hero.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(170.dp, 180.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = hero.name)
    }
}

@Composable
fun IconWithText(
    iconResourceId: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Box(
            modifier = Modifier.size(60.dp, 60.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_shape_all),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Image(
                    painter = painterResource(id = iconResourceId),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Text(text = text, color = Color.White, modifier = Modifier.padding(top = 10.dp))
    }
}

