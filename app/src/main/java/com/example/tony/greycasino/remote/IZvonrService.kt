package com.example.tony.greycasino.remote

import com.google.gson.JsonObject
import io.reactivex.Single

import retrofit2.http.Url
import okhttp3.ResponseBody
import retrofit2.http.GET



/**
 * Created By tony
 */

interface IZvonrService {

    @GET
    fun downloadFileWithDynamicUrlSync(@Url fileUrl: String): Single<ResponseBody>
}