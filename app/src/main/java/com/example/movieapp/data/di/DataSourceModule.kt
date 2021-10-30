package com.example.movieapp.data.di

import com.example.movieapp.data.api.*
import com.example.movieapp.data.db.MovieDao
import com.example.movieapp.data.repository.datasource.LocalDataSource
import com.example.movieapp.data.repository.datasource.RemoteDataSource
import com.example.movieapp.data.repository.datasourceimpl.LocalDataSourceImpl
import com.example.movieapp.data.repository.datasourceimpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(movieDao: MovieDao): LocalDataSource {
        return LocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource(
        getTrendingMoviesAPI: GetMovieListApi,
        getCreditsAPI: GetCreditsApi,
        getMovieTrailerAPI: GetMovieTrailerApi,
        getPersonDataAPI: GetPersonDataApi,
        getMovieDetailsAPI: GetMovieDetailsApi
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            getTrendingMoviesAPI,
            getCreditsAPI,
            getMovieTrailerAPI,
            getPersonDataAPI,
            getMovieDetailsAPI
        )
    }


}