package com.mantushnikita.disneyherocompose.ui.list

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mantushnikita.disneyherocompose.R
import com.mantushnikita.disneyherocompose.model.Hero
import com.mantushnikita.disneyherocompose.navigation.HERO_SCREEN

@Composable
fun ListScreen(
    navigationController: NavHostController,
    viewModel: ListViewModel = hiltViewModel()
) {
    val list = viewModel.listHero.observeAsState()
    viewModel.loadListHeroes()
    Image(
        painter = painterResource(id = R.drawable.ic_cloud), contentDescription = null,
        contentScale = ContentScale.Fit
    )
    Column {
        Row(modifier = Modifier.padding(top = 80.dp)) {
            IconWithText(iconResourceId = R.drawable.ic_all, text = "All")
            IconWithText(iconResourceId = R.drawable.ic_favorite, text = "Favorite")
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            items(count = list.value?.size ?: 0) { index ->
                val hero = list.value?.getOrNull(index)
                if (hero != null) {
                    HeroItem(hero = hero) { id ->
                        navigationController.navigate("$HERO_SCREEN/$id")
                    }
                }
            }
        }
    }
}

@Composable
fun HeroItem(hero: Hero, onClick: (idHero: Int) -> Unit) {
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
