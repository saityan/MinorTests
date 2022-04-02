package com.geekbrains.tests.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.geekbrains.tests.R
import com.geekbrains.tests.databinding.FragmentDetailsBinding
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.presenter.details.PresenterDetailsContract
import java.util.*

class DetailsFragment : Fragment(), ViewDetailsContract {
    private var detailsPresenter: PresenterDetailsContract = DetailsPresenter()
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsPresenter.onAttach(this)
        setUI()
    }

    private fun setUI() {
        arguments?.let {
            val count = it.getInt(TOTAL_COUNT_EXTRA, 0)
            detailsPresenter.setCount(count)
            this@DetailsFragment.setCount(count)
        }
        binding.decrementButton.setOnClickListener { detailsPresenter.onDecrement() }
        binding.incrementButton.setOnClickListener { detailsPresenter.onIncrement() }
    }

    private fun setCountText(count: Int) {
        binding.totalCountTextViewDetails.text =
            String.format(Locale.getDefault(), getString(R.string.results_count), count)
    }

    override fun onDestroy() {
        super.onDestroy()
        detailsPresenter.onDetach()
        _binding = null
    }

    override fun setCount(count: Int) {
        setCountText(count)
    }

    companion object {
        private const val TOTAL_COUNT_EXTRA = "TOTAL_COUNT_EXTRA"

        @JvmStatic
        fun newInstance(counter: Int) =
            DetailsFragment().apply { arguments = bundleOf(TOTAL_COUNT_EXTRA to counter) }
    }
}
