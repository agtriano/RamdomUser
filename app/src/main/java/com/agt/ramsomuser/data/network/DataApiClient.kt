package com.agt.ramsomuser.data.network


import com.agt.ramsomuser.data.model.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface DataApiClient {

    @Headers("Accept:application/json", "Content-Type:application/json")
    @GET("api/?results=5000")
    suspend fun getAllContacts(): Response<Results>

}
