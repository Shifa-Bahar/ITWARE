package com.example.myapplication.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class MovieRequestModel(
  @SerializedName("user_name") val user_name : String
) : Parcelable