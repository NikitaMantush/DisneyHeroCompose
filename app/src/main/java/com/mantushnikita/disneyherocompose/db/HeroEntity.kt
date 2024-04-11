package com.mantushnikita.disneyherocompose.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mantushnikita.disneyherocompose.model.FilmContent

@Entity(tableName = "HeroEntity")
data class HeroEntity(
    @PrimaryKey(autoGenerate = true) val _id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean = false
)