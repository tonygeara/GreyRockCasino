package com.example.tony.greycasino.remote


import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

/**
 * Created By Tony Geara
 * May 30, 2019
 *
 */

class RemoteDataManager :IRemoteSource {

    private lateinit var mRetrofitInstance: Retrofit

    private lateinit var iApiService: IZvonrService

    val BASE_URL = "http://www.greyrockcasino.com/"

    init {
        initializeBaseUrlOfWebServer()
    }

    private fun initializeBaseUrlOfWebServer() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.writeTimeout(30, TimeUnit.SECONDS)

        mRetrofitInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build()

        iApiService = mRetrofitInstance.create(IZvonrService::class.java)

    }
    override fun downloadFile(fileUrl: String) = iApiService.downloadFileWithDynamicUrlSync(fileUrl)
}
