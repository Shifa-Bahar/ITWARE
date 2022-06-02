package com.example.myapplication.api

import com.example.myapplication.Urls
import com.example.myapplication.Urls.LOGIN
import com.example.myapplication.Urls.MOVIEWISHLIST
import com.example.myapplication.Urls.PHOTO
import com.example.myapplication.Urls.TODO
import com.example.myapplication.Urls.USER
import com.example.myapplication.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @POST(USER)
    suspend fun getValue(@Body body: MovieRequestModel): Response<UserDataModel?>

    @POST(MOVIEWISHLIST)
    suspend fun getMovieWishlist(@Body body: MovieWishlistRequestModel): Response<MovieWishlistModel?>

    @POST(LOGIN)
    suspend fun getLogin(@Body body: LoginRequestModel): Response<UserDataModel?>

    @GET(TODO)
    suspend fun getTodo(): Response<ArrayList<TodoResponseModel>?>


    @GET(PHOTO)
    suspend fun getPhoto(): Response<ArrayList<Photo>?>

}


