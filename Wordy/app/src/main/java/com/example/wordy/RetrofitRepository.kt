package com.example.wordy

import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepository: Repository {

    val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val api: FreeDictionaryApi
    = retrofit.create(FreeDictionaryApi::class.java)


   override fun getDataFromApi(word: String): Single<List<WordData>> {
        return Single.create { subscriber ->
            api.getDefinitionsAndSynonyms(word).enqueue(
                object: Callback<List<WordData>> {
                    override fun onResponse(
                        call: Call<List<WordData>>,
                        response: Response<List<WordData>>) {
                        if (response.isSuccessful) {
                            subscriber.onSuccess(response.body())
                        }
                    }
                    override fun onFailure(call: Call<List<WordData>>, t: Throwable) {
                            subscriber.onError(t)
                    }
                })
        }
    }

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/"
        const val BASE_URL_OTHER = "https://api.dictionaryapi.dev/"
    }
}