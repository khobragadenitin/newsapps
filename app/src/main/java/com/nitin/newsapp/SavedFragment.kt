package com.nitin.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nitin.newsapp.data.util.Resource
import com.nitin.newsapp.databinding.FragmentSavedBinding
import com.nitin.newsapp.presentation.adapter.NewsAdapter
import com.nitin.newsapp.presentation.viewmodel.NewsViewModel


class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var viewModel : NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        initRecyclerView()
        viewSavedNewsData()

    }

    private fun initRecyclerView() {
        adapter = NewsAdapter()
        adapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article",it)
            }
            findNavController().navigate(R.id.action_savedFragment_to_infoFragment,bundle)
        }
        binding.savedRecyView.adapter = adapter
        binding.savedRecyView.layoutManager = LinearLayoutManager(activity)

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = adapter.differ.currentList[position]
                viewModel.deleteSavedNews(article)
                Snackbar.make(view!!,"Deleted Sucessfully",Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("UNDO"){
                            viewModel.saveArticle(article)
                        }
                        show()
                    }

            }

        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.savedRecyView)
        }
    }

    fun viewSavedNewsData(){
        viewModel.getSavedArticles().observe(viewLifecycleOwner, Observer {
            adapter.differ.submitList(it)
        } )
    }




}