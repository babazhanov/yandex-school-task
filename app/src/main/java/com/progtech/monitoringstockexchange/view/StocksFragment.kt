package com.progtech.monitoringstockexchange.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.progtech.monitoringstockexchange.R
import com.progtech.monitoringstockexchange.model.Card
import com.progtech.monitoringstockexchange.viewmodel.StocksViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class StocksFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val stocksViewModel: StocksViewModel by viewModels()
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val cardAdapter = CardAdapter(requireContext(), stocksViewModel)

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = cardAdapter

        mSwipeRefreshLayout = view.findViewById(R.id.layout_main)
        mSwipeRefreshLayout.setOnRefreshListener(this)
        mSwipeRefreshLayout.isRefreshing = true

        stocksViewModel.getStocks().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                val stocks = it.take(16)
                cardAdapter.addCards(
                    stocks.map {
                        Card(it.symbol)
                    },
                    true
                )
            }
            mSwipeRefreshLayout.isRefreshing = false
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = StocksFragment()
    }

    override fun onRefresh() {
        stocksViewModel.fetchStockSymbol()
        mSwipeRefreshLayout.isRefreshing = true
    }
}