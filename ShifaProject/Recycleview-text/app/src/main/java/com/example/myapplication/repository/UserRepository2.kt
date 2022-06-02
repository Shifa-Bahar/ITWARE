package com.example.myapplication.repository

import com.example.myapplication.base.BaseRepository
import com.example.myapplication.api.Api2
import javax.inject.Inject

class UserRepository2 @Inject constructor(private val api: Api2): BaseRepository() {
    suspend fun getAlbum() =
        getResult { api.getAlbum() }


}