package com.ssu.portfolio.newsreaderapp.db

import androidx.room.TypeConverter
import com.ssu.portfolio.newsreaderapp.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}
