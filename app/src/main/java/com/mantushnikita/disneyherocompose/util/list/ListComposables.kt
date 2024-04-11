package com.mantushnikita.disneyherocompose.util.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mantushnikita.disneyherocompose.model.Hero
import com.mantushnikita.disneyherocompose.navigation.HERO_SCREEN

@Composable
fun SetList(list: List<Hero>, navigationController: NavHostController) {
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
