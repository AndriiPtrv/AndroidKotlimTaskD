package com.example.androidkotlintaskd.network

import com.example.androidkotlintaskd.network.model.Drinks
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object ApiService {
    private val END_POINT: String? = "https://www.thecocktaildb.com/api/json/v1/1/"
    private var drinksApi: DrinksApi
    fun getData(): Observable<Drinks>{
        return drinksApi.allDrinks()
    }

    interface DrinksApi {
        @GET("filter.php?a=Alcoholic")
        fun allDrinks(): Observable<Drinks>
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(END_POINT)
            .client(client)
            .build()
        drinksApi = retrofit.create(DrinksApi::class.java)
    }
}