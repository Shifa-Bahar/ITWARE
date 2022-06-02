package com.example.myapplication.network
import org.json.JSONObject
import retrofit2.Response

abstract class BaseDataSource {
  protected suspend fun <T> getResult(
    call: suspend () -> Response<T?>
  ): RequestResult<T?> {
      try {
        val response = call()
        if (response.isSuccessful && response.code() < 400) {
          val body = response.body()
          return RequestResult.success(body)

        }
        val errorBody = JSONObject(
          response.errorBody()!!
            .charStream()
            .readText()
        )
        return if (errorBody.getString("message").isNullOrEmpty()){
          error("Something went wrong please try again later")
        }else{
          error(errorBody.getString("message"))
        }

      } catch (e: Exception) {
        return error(e.message ?: e.toString())
      }
    }

  }

  private fun <T> error(message: String): RequestResult<T> {
    return RequestResult.error(message)
  }


