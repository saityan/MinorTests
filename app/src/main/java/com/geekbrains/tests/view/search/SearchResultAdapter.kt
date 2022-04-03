package com.geekbrains.tests.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.tests.databinding.ListItemBinding
import com.geekbrains.tests.model.SearchResult
import com.geekbrains.tests.view.search.SearchResultAdapter.SearchResultViewHolder

internal class SearchResultAdapter (
    private var results: List<SearchResult>
) : RecyclerView.Adapter<SearchResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val searchResult: SearchResult = results[position]
        holder.bind(searchResult)
    }

    override fun getItemCount(): Int = results.size

    fun updateResults(results: List<SearchResult>) {
        this.results = results
        notifyDataSetChanged()
    }

    internal class SearchResultViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind (searchResult: SearchResult) {
            binding.repositoryName.text = searchResult.name
        }
    }
}
