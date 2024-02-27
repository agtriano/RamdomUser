package com.agt.ramsomuser.data

import android.util.Log
import com.agt.ramsomuser.data.model.Results

import com.agt.ramsomuser.data.network.DataService
import retrofit2.Response

class DataRepository {


    private val api = DataService()

    suspend fun getAllContacts(): Results? {

        val response: Response<Results>? = api.getAllContacts()
        val datam: Results = response?.body()!!
        
        ContactsListProvider.dataList = datam
        tolog("getAllContacts response " + response)
        return  ContactsListProvider.dataList
    }





    fun tolog(msg: String) {
        Log.d("TEST", " data DataRepository  + $msg")
    }


}
