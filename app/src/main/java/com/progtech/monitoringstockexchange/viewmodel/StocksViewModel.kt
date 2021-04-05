package com.progtech.monitoringstockexchange.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progtech.monitoringstockexchange.model.ApiService
import com.progtech.monitoringstockexchange.model.CompanyProfile
import com.progtech.monitoringstockexchange.model.Quote
import com.progtech.monitoringstockexchange.model.StockSymbol
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StocksViewModel : ViewModel() {

    private val stocks: MutableLiveData<List<StockSymbol>> by lazy {
        MutableLiveData<List<StockSymbol>>().apply { fetchStockSymbol() }
    }

    fun getStocks(): LiveData<List<StockSymbol>> = stocks

    fun fetchStockSymbol() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiService.api.stockSymbol()
                stocks.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
                stocks.postValue(ArrayList<StockSymbol>())
            }
        }
    }

    fun getCompanyProfile(symbol: String): LiveData<CompanyProfile> {
        val profile = MutableLiveData<CompanyProfile>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiService.api.companyProfile(symbol)
                profile.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return profile
    }

    fun getQuote(symbol: String): LiveData<Quote> {
        val quote = MutableLiveData<Quote>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiService.api.quote(symbol)
                quote.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return quote
    }
}