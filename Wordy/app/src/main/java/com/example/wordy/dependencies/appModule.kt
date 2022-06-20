package com.example.wordy.dependencies

import com.example.wordy.data.FreeDictionaryApi
import com.example.wordy.data.Repository
import com.example.wordy.data.RetrofitRepository
import com.example.wordy.ui.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
val appModule = module {
  //Room
  fun provideDatabase(application: Application): SavedWordsDatabase {
      return Room.databaseBuilder(
          application,
          SavedWordsDatabase::class.java,
          "saved_word_database"
      ).build()
  }

    fun provideDao(database: SavedWordsDatabase): WordDao{
        return database.getWordDao()
    }

    single {provideDatabase(androidApplication())}
    single {provideDao(get())}
    single {DatabaseInteractor(get())}

    //Retrofit
    single(named("api_url")) {"https://api.dictionaryapi.dev/"}
    single<Repository>{ RetrofitRepository(get()) }
    single<FreeDictionaryApi> {get<Retrofit>().create(FreeDictionaryApi::class.java)}
    single { provideOkHttpClient()}
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(named("api_url")))
            .client(get())
            .addConverterFactory(get())
            .build()}
    //factories
    factory<Converter.Factory>{ GsonConverterFactory.create()}
    //ViewModel
    viewModel {MainViewModel(get(),get())}
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}
