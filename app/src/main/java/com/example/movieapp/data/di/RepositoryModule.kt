package com.example.movieapp.data.di

import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.data.repository.datasource.LocalDataSource
import com.example.movieapp.data.repository.datasource.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
    ): MovieRepository {
        return MovieRepositoryImpl(localDataSource, remoteDataSource)
    }
}
