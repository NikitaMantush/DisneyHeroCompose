package com.mantushnikita.disneyherocompose.ui.hero

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import coil.compose.AsyncImage
import com.mantushnikita.disneyherocompose.model.Hero
import com.mantushnikita.disneyherocompose.ui.theme.Purple80

@Composable
fun HeroScreen(id: Int, viewModel: HeroViewModel = hiltViewModel()) {
    viewModel.processAction(HeroAction.LoadById(id))
    heroScreenState(state = viewModel.state)

}
@Composable
fun heroScreenState(state: MutableLiveData<HeroState>){
    state.observeAsState().value?.let {
        when(it){
            is HeroState.HeroLoaded ->{
                createHero(hero = it.hero)
            }
            is HeroState.Error ->{
                Toast.makeText(LocalContext.current, it.error, Toast.LENGTH_LONG).show()
            }
            is HeroState.Loading ->{
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
        }
    }
}
@Composable
fun createHero(hero: Hero){
    Column {
        AsyncImage(
            model = hero.imageUrl, contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = hero.name ?: "", fontSize = 34.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 10.dp)
        )

        LazyColumn(modifier = Modifier.padding(start = 16.dp)) {
            if (hero.filmContent.find { it.name == "film" } != null) {
                item {
                    Text(
                        text = "Films",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 6.dp)
                    )
                }
                val filmContent = hero.filmContent.find { it.name == "film" }
                items(filmContent?.film ?: arrayListOf()) {
                    Text(text = filmContent?.film.toString(), fontSize = 20.sp)
                }
            }
            if (hero.filmContent.find { it.name == "shortFilms" } != null) {
                item {
                    Text(
                        text = "Short Films", fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 6.dp)
                    )
                }
                val filmContent = hero.filmContent.find { it.name == "shortFilms" }
                items(filmContent?.film ?: arrayListOf()) {
                    Text(text = filmContent?.film.toString(), fontSize = 20.sp)

                }
            }
            if (hero.filmContent.find { it.name == "tvShows" } != null) {
                item {
                    Text(
                        text = "TV Shows", fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 6.dp)
                    )
                }
                val filmContent = hero.filmContent.find { it.name == "tvShows" }
                items(filmContent?.film ?: arrayListOf()) {
                    Text(text = filmContent?.film.toString(), fontSize = 20.sp)

                }
            }
            if (hero.filmContent.find { it.name == "videoGames" } != null) {
                item {
                    Text(
                        text = "Video Games", fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 6.dp)
                    )
                }
                val filmContent = hero.filmContent.find { it.name == "videoGames" }
                items(filmContent?.film ?: arrayListOf()) {
                    Text(text = filmContent?.film.toString(), fontSize = 20.sp)

                }
            }
        }
    }
}