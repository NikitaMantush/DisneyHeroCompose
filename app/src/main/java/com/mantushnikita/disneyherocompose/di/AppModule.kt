package com.mantushnikita.disneyherocompose.di

import android.content.Context
import androidx.room.Room
import com.mantushnikita.disneyherocompose.db.AppDataBase
import com.mantushnikita.disneyherocompose.db.HeroDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "dataBase")
            .build()
    }

    @Singleton
    @Provides
    fun provideItemDao(appDataBase: AppDataBase): HeroDao {
        return appDataBase.getHeroDao()
    }

}