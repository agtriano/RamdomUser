package com.agt.ramsomuser.data.network

import android.util.Log

import com.agt.ramsomuser.core.RetrofitHelper
import com.agt.ramsomuser.data.model.Results

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DataService {

    private val retrofit = RetrofitHelper.getRetrofit()


    suspend fun getAllContacts(): Response<Results>? {
        return withContext(Dispatchers.IO) {
            val response: Response<Results> = retrofit.create(DataApiClient::class.java).getAllContacts()
            tolog(" response " + response)
            response
        }
    }


    fun tolog(msg: String) {
        Log.d("TEST", " data service  + $msg")
    }
}
