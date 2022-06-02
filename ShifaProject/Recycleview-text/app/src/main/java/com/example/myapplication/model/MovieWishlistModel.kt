package com.example.myapplication.model

data class MovieWishlistModel(
    val message : String,
    val data : ArrayList<Moviemodel>? = ArrayList()
)