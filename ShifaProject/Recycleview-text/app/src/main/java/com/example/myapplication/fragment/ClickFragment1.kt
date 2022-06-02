package com.example.myapplication.fragment

interface ClickFragment1 {
    fun getLogin()
}
interface CLickFragment2 {
    fun onClickGotoFragment1()
    fun getUserDetails()
    fun getMovieWishlist(id: Int)
    fun getLogin()
    fun onClickMovie(id: Int)
}
