package com.nitin.newsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.nitin.newsapp.data.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun getArticleDao() : ArticleDao
}