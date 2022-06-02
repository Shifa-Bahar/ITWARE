package com.example.myapplication.repository

import com.example.myapplication.base.BaseRepository
import com.example.myapplication.api.Api
import com.example.myapplication.model.LoginRequestModel
import com.example.myapplication.model.MovieRequestModel
import com.example.myapplication.model.MovieWishlistRequestModel
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: Api): BaseRepository() {
    suspend fun getValue(body: MovieRequestModel) =
        getResult { api.getValue(body) }

    suspend fun getMovieWishlist(body: MovieWishlistRequestModel) =
        getResult { api.getMovieWishlist(body) }

    suspend fun getLogin(body: LoginRequestModel) =
        getResult { api.getLogin(body) }
    suspend fun getTodo() =
        getResult { api.getTodo() }

    suspend fun getPhoto() =
        getResult { api.getPhoto() }

}