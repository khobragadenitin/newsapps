package com.nitin.newsapp.data.db

import androidx.room.TypeConverter
import com.nitin.newsapp.data.model.Source

class Converter {

    @TypeConverter
    fun fromSourceToString(source: Source) : String{
        return source.name
    }

    @TypeConverter
    fun fromSourceToSource(string: String) : Source{
        return Source(string,string)
    }
}