package com.example.myapplication.model

data class UserDataModel(
    val message : String,
    val data : ArrayList<Moviemodel>? = ArrayList()
)