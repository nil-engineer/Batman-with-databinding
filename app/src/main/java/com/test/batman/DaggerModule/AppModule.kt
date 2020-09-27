package com.test.batman.DaggerModule

import android.content.Context
import android.net.ConnectivityManager
import com.test.batman.BatmanAPI
import com.test.batman.Utilities.CheckNetwork
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    private val context: Context
    private val httpClient = OkHttpClient.Builder()
//    var client: OkHttpClient? = null
    private val BASE_URL = "http://www.omdbapi.com/"
    constructor(context: Context) {
        this.context = context
    }

    @Provides //scope is not necessary for parameters stored within the module
    fun context(): Context? {
        return context
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): BatmanAPI {
        return retrofit.create(BatmanAPI::class.java)
    }

//    @Provides
//    @Singleton
//    fun providesCheckNetwork(context: Context): CheckNetwork{
//        return CheckNetwork()
//    }

}