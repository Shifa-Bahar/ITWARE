package com.example.myapplication.api

import com.example.myapplication.Urls
import com.example.myapplication.model.albumModel
import retrofit2.Response
import retrofit2.http.GET

interface Api2 {
    @GET(Urls.ALBUM)
    suspend fun getAlbum(): Response<ArrayList<albumModel>?>
}