package com.mantushnikita.disneyherocompose.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HeroDao {
    @Insert
    suspend fun addHero(hero: HeroEntity)

    @Delete
    suspend fun deleteHero(hero: HeroEntity)

    @Update
    suspend fun updateHero(hero: HeroEntity)

    @Query("SELECT * FROM HeroEntity WHERE _id == :id LiMIT 1")
    suspend fun getHeroId(id: Int): HeroEntity?

    @Query("SELECT * FROM HeroEntity")
    suspend fun getAllHero(): List<HeroEntity>
    @Query("SELECT isFavorite FROM HeroEntity WHERE _id == :id")
    suspend fun isHeroFavorite(id: Int): Boolean?

}