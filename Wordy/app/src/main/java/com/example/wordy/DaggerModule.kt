package com.example.wordy.dependencies

import android.content.Context
import com.example.wordy.data.FreeDictionaryApi
import com.example.wordy.data.Repository
import com.example.wordy.data.RetrofitRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DaggerModule(val context: Context) {

    @Singleton
    @Provides
    fun provideGithubApi(retrofit: Retrofit): FreeDictionaryApi {
        return retrofit.create(FreeDictionaryApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitRepo(api: FreeDictionaryApi): Repository {
        return RetrofitRepository(api)
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava3CallAdapterFactory.create()
    }

    /*
    @Provides
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }*/

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            //.addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideBaseUrl(): String = "https://api.dictionaryapi.dev/"

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
    }
}