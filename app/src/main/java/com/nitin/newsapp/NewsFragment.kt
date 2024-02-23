package com.nitin.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nitin.newsapp.data.util.Resource
import com.nitin.newsapp.databinding.FragmentNewsBinding
import com.nitin.newsapp.presentation.adapter.NewsAdapter
import com.nitin.newsapp.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {

    private lateinit var adapter: NewsAdapter
    private lateinit var binding: FragmentNewsBinding
    private val country = "in"
    private val page = 1
    private lateinit var viewModel : NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        initRecyclerView()
        viewNewsData()

        initSearchView()


    }

    fun viewNewsData(){
        viewModel.getNewsHeadline(country, page)
        viewModel.newsHeadline.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        hideProgressBar()
                        adapter.differ.submitList(it.articles.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity,"Error : $it ",Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }

            }
        })
    }

    fun initSearchView(){
        binding.svNews.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getSearchNewsHeadline(country,query, page)
                viewSearchNewsData()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                MainScope().launch {
                    delay(2000)
                    viewModel.getSearchNewsHeadline(country,newText, page)
                    viewSearchNewsData()
                }

                return false
            }

        })

        binding.svNews.setOnCloseListener(object : SearchView.OnCloseListener,
            android.widget.SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                initRecyclerView()
                viewNewsData()
                return false
            }

        })
    }


    fun viewSearchNewsData(){
        viewModel.searchHeadline.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        hideProgressBar()
                        adapter.differ.submitList(it.articles.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity,"Error : $it ",Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }

            }
        })
    }

    private fun initRecyclerView() {
        adapter = NewsAdapter()
        adapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article",it)
            }
            findNavController().navigate(R.id.action_newsFragment_to_infoFragment,bundle)
        }
        binding.newsRecyclerView.adapter = adapter
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.GONE
    }


}

