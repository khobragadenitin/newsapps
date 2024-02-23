package com.nitin.newsapp.data.db

import androidx.room.*
import com.nitin.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("Select * from ARTICLES")
    fun getSavedNews() : Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}