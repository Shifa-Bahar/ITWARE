package com.example.myapplication.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.myapplication.network.RequestResult.Status.ERROR
import com.example.myapplication.network.RequestResult.Status.SUCCESS
import kotlinx.coroutines.Dispatchers


fun <A> performNwOperation(networkCall: suspend () -> RequestResult<A>): LiveData<RequestResult<A>> =
  liveData(Dispatchers.IO) {
    emit(RequestResult.loading())
    val responseStatus = networkCall.invoke()
    if (responseStatus.status == SUCCESS) {
      emit(RequestResult.success(responseStatus.data!!))
    } else if (responseStatus.status == ERROR) {
      emit(RequestResult.error(responseStatus.message!!))
    }
  }