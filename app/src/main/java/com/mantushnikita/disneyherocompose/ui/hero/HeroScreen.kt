package com.mantushnikita.disneyherocompose.ui.hero

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun HeroScreen(id: Int, viewModel: HeroViewModel = hiltViewModel()) {
    val hero = viewModel.hero.observeAsState()
    viewModel.getHeroById(id)
    Column {
        AsyncImage(
            model = hero.value?.imageUrl, contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = hero.value?.name ?: "", fontSize = 34.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 10.dp)
        )

        LazyColumn(modifier = Modifier.padding(start = 16.dp)) {
            if (hero.value?.filmContent?.find { it.name == "film" } != null) {
                item {
                    Text(
                        text = "Films",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 6.dp)
                    )
                }
                val filmContent = hero.value?.filmContent?.find { it.name == "film" }
                items(filmContent?.film ?: arrayListOf()) {
                    Text(text = filmContent?.film.toString(), fontSize = 20.sp)
                }
            }
            if (hero.value?.filmContent?.find { it.name == "shortFilms" } != null) {
                item {
                    Text(
                        text = "Short Films", fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 6.dp)
                    )
                }
                val filmContent = hero.value?.filmContent?.find { it.name == "shortFilms" }
                items(filmContent?.film ?: arrayListOf()) {
                    Text(text = filmContent?.film.toString(), fontSize = 20.sp)

                }
            }
            if (hero.value?.filmContent?.find { it.name == "tvShows" } != null) {
                item {
                    Text(
                        text = "TV Shows", fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 6.dp)
                    )
                }
                val filmContent = hero.value?.filmContent?.find { it.name == "tvShows" }
                items(filmContent?.film ?: arrayListOf()) {
                    Text(text = filmContent?.film.toString(), fontSize = 20.sp)

                }
            }
            if (hero.value?.filmContent?.find { it.name == "videoGames" } != null) {
                item {
                    Text(
                        text = "Video Games", fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 6.dp)
                    )
                }
                val filmContent = hero.value?.filmContent?.find { it.name == "videoGames" }
                items(filmContent?.film ?: arrayListOf()) {
                    Text(text = filmContent?.film.toString(), fontSize = 20.sp)

                }
            }
        }
    }
}