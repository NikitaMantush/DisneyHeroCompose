package com.mantushnikita.disneyherocompose.util

import com.mantushnikita.disneyherocompose.model.FilmContent
import com.mantushnikita.disneyherocompose.model.Hero
import com.mantushnikita.disneyherocompose.model.HeroInfo

fun HeroInfo.toHero(): Hero {
    val filmContent = arrayListOf<FilmContent>()
    if (!films.isNullOrEmpty()){
        filmContent.add(
            FilmContent("film", films)
        )
    }
    if (!shortFilms.isNullOrEmpty()){
        filmContent.add(
            FilmContent("shortFilms", shortFilms)
        )
    }
    if (!tvShows.isNullOrEmpty()){
        filmContent.add(
            FilmContent("tvShows", tvShows)
        )
    }
    if (!videoGames.isNullOrEmpty()){
        filmContent.add(
            FilmContent("videoGames", videoGames)
        )
    }
    return Hero(
        _id,
        name,
        imageUrl,
        filmContent
    )
}