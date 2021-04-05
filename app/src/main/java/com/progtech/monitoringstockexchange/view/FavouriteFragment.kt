package com.progtech.monitoringstockexchange.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.progtech.monitoringstockexchange.R
import com.progtech.monitoringstockexchange.viewmodel.StocksViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class FavouriteFragment : Fragment() {

    private val stocksViewModel: StocksViewModel by viewModels()

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

    companion object {

        @JvmStatic
        fun newInstance() = FavouriteFragment()

    }
}