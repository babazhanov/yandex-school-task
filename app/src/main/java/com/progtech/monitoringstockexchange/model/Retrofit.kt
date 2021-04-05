package com.progtech.monitoringstockexchange.model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.progtech.monitoringstockexchange.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException

class ApiKeyInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url.newBuilder()
            .addQueryParameter("token", BuildConfig.API_TOKEN)
            .build()
        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }
}

interface FinhubApi {
    @GET("v1/stock/symbol?exchange=US") // MOSCOW EXCHANGE
    suspend fun stockSymbol(): List<StockSymbol>

    @GET("v1/stock/profile2")
    suspend fun companyProfile(@Query("symbol") symbol: String): CompanyProfile

    @GET("v1/quote")
    suspend fun quote(@Query("symbol") symbol: String): Quote
}

object ApiService {

    val client: OkHttpClient by lazy { getOkHttpClient() }
    val retrofit: Retrofit by lazy { getRetrofitImpl() }

    private val gson: Gson by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }
    val api: FinhubApi by lazy { getApiService() }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        builder.addInterceptor(ApiKeyInterceptor())
        if (!BuildConfig.BUILD_TYPE.contains("release")) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return builder.build()
    }

    private fun getRetrofitImpl(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL) // need for interceptors
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun getApiService(): FinhubApi {
        return retrofit.create(FinhubApi::class.java)
    }
}