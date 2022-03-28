package com.geekbrains.tests.view.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.tests.R
import com.geekbrains.tests.databinding.ActivityMainBinding
import com.geekbrains.tests.model.SearchResult
import com.geekbrains.tests.presenter.search.PresenterSearchContract
import com.geekbrains.tests.presenter.search.SearchPresenter
import com.geekbrains.tests.repository.FakeGitHubRepository
import com.geekbrains.tests.repository.RepositoryContract
import com.geekbrains.tests.view.details.DetailsActivity
import java.util.*

class MainActivity : AppCompatActivity(), ViewSearchContract {

    private lateinit var binding: ActivityMainBinding
    private val adapter = SearchResultAdapter()
    private val presenter: PresenterSearchContract = SearchPresenter(createRepository())
    private var totalCount: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    private fun setUI() {
        binding.toDetailsActivityButton.setOnClickListener {
            startActivity(totalCount?.let { totalCount ->
                DetailsActivity.getIntent(this, totalCount)
            })
        }
        setSearchButtonListener()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    private fun setSearchButtonListener() {
        binding.searchRepositoryButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            if (query.isNotBlank())
                presenter.searchGitHub(query)
            else Toast.makeText(
                this@MainActivity,
                getString(R.string.enter_search_word),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int
    ) {
        with(binding.totalCountTextViewDetails) {
            visibility = View.VISIBLE
            text = String.format(
                Locale.getDefault(),
                getString(R.string.results_count),
                totalCount
            )
        }

        this.totalCount = totalCount
        adapter.updateResults(searchResults)
    }

    override fun displayError() {
        Toast.makeText(this, getString(R.string.undefined_error), Toast.LENGTH_SHORT).show()
    }

    override fun displayError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun displayLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun setCount(count: Int) {
        this.totalCount = count
    }

    override fun setCountToNull() {
        this.totalCount = null
    }

    private fun createRepository(): RepositoryContract = FakeGitHubRepository()
}
