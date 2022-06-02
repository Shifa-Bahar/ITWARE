package com.example.myapplication.network

import com.example.myapplication.network.RequestResult.Status.ERROR
import com.example.myapplication.network.RequestResult.Status.LOADING
import com.example.myapplication.network.RequestResult.Status.SUCCESS

data class RequestResult<out T>(val status: Status,
                                val data: T?,
                                val message: String?) {
  enum class Status {
    SUCCESS,
    ERROR,
    LOADING
  }

  companion object {
    fun <T> success(data: T): RequestResult<T> {
      return RequestResult(SUCCESS, data, null)
    }

    fun <T> error(message: String,
      data: T? = null): RequestResult<T> {
      return RequestResult(ERROR, data, message)
    }

    fun <T> loading(data: T? = null): RequestResult<T> {
      return RequestResult(LOADING, data, null)
    }
  }
}