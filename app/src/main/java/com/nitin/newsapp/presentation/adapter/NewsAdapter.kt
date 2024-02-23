package com.nitin.newsapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nitin.newsapp.data.model.Article
import com.nitin.newsapp.databinding.NewsListItemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)

    inner class NewsViewHolder(
        val binding: NewsListItemBinding
    ):
        RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            Log.i("MYTAG","came here ${article.title}")
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source?.name

            Glide.with(binding.ivArticleImage.context).
            load(article.urlToImage).
            into(binding.ivArticleImage)

            binding.root.setOnClickListener{
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsListItemBinding.inflate(inflater,parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var  onItemClickListener : ((Article) -> Unit)? =null

    fun setOnItemClickListener(listener : ((Article) -> Unit)) {
        this.onItemClickListener = listener
    }



}