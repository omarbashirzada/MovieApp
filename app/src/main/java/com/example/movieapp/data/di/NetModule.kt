package com.example.movieapp.data.di

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideGetTrendingMoviesAPI(retrofit: Retrofit): GetMovieListApi {
        return retrofit.create(GetMovieListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetCreditsAPI(retrofit: Retrofit): GetCreditsApi {
        return retrofit.create(GetCreditsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetPersonDataAPI(retrofit: Retrofit): GetPersonDataApi {
        return retrofit.create(GetPersonDataApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetMovieTrailerAPI(retrofit: Retrofit): GetMovieTrailerApi {
        return retrofit.create(GetMovieTrailerApi::class.java)
    }
    @Provides
    @Singleton
    fun provideGetMovieDetailsAPI(retrofit: Retrofit): GetMovieDetailsApi {
        return retrofit.create(GetMovieDetailsApi::class.java)
    }

}
