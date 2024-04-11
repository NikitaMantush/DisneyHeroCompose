package com.mantushnikita.disneyherocompose.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HeroEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getHeroDao(): HeroDao
}