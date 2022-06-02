package com.example.myapplication.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class LoginRequestModel(
  @SerializedName("user_name") val user_name : String,
  @SerializedName("password") val password : String,
) : Parcelable