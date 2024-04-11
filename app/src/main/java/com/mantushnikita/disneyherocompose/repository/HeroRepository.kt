package com.mantushnikita.disneyherocompose.repository

import com.mantushnikita.disneyherocompose.db.HeroDao
import com.mantushnikita.disneyherocompose.db.HeroEntity
import com.mantushnikita.disneyherocompose.model.Hero
import com.mantushnikita.disneyherocompose.network.Api
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val api: Api,
    private val heroDao: HeroDao
) {
    suspend fun getHeroes() = api.getHeroes()

    suspend fun getHeroById(id: Int) = api.getHeroById(id)

    suspend fun add(hero: Hero) {
        heroDao.addHero(
            HeroEntity(
                hero._id,
                hero.name,
                hero.imageUrl,
                true
            )
        )
    }
    suspend fun isHeroFavorite(heroId: Int): Boolean {
        return heroDao.isHeroFavorite(heroId) ?: false
    }
    suspend fun getHeroList(): List<Hero> {
        return (heroDao.getAllHero().map {
            Hero(
                it._id, it.name, it.imageUrl, arrayListOf(), it.isFavorite
            )
        } as? ArrayList<Hero>) ?: arrayListOf()
    }

    suspend fun deleteHero(id: Int) {
        heroDao.deleteHero(HeroEntity(id, "", "", false))
    }

}