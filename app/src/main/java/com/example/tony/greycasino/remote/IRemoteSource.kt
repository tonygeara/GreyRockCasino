package com.example.tony.greycasino.remote

import com.google.gson.JsonObject
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * Created By Tony
 */

interface IRemoteSource {

    fun downloadFile(fileUrl: String)
            : Single<ResponseBody>


}