package com.ssu.portfolio.newsreaderapp.ui.Fragements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ssu.portfolio.newsreaderapp.R
import com.ssu.portfolio.newsreaderapp.adaptor.NewsAdaptor
import com.ssu.portfolio.newsreaderapp.databinding.FragmentFavoritesBinding
import com.ssu.portfolio.newsreaderapp.ui.NewsActivity
import com.ssu.portfolio.newsreaderapp.ui.NewsViewModel

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdaptor: NewsAdaptor
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as NewsActivity).newsViewModel
        setupFavoriteRecycler()

        newsViewModel.getFavoritesNews().observe(viewLifecycleOwner, Observer { articles ->
            newsAdaptor.differ.submitList(articles)
        })

        newsAdaptor.setOnItemClickListener { article ->
            val bundle = Bundle().apply {
                putSerializable("article", article)
            }
            findNavController().navigate(
                R.id.action_favoritesFragment_to_articlesFragment,
                bundle
            )
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdaptor.differ.currentList[position]

                newsViewModel.deleteArticle(article)

                Snackbar.make(view, "Article deleted successfully!", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        newsViewModel.deleteArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerFavourites)
        }
    }

    private fun setupFavoriteRecycler() {
        newsAdaptor = NewsAdaptor()
        binding.recyclerFavourites.apply {
            adapter = newsAdaptor
            layoutManager = LinearLayoutManager(activity)
        }
    }
}