package com.example.movieapp.ui.home.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.ActorModel
import com.example.movieapp.data.model.Cast
import com.example.movieapp.data.model.MovieDetailsModel
import com.example.movieapp.data.model.TrendingMoviesModel
import com.suret.moviesapp.domain.usecase.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.movieapp.data.model.*
import com.example.movieapp.util.AppUtil.isNetworkAvailable
import com.example.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val deleteMoviesUseCase: DeleteMoviesUseCase,
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getCreditsUseCase: GetCreditsUseCase,
    private val getFavoriteMovieByIdUseCase: GetFavoriteMovieByIdUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val getMovieTrailerUseCase: GetMovieTrailerUseCase,
    private val getPersonDataUseCase: GetPersonDataUseCase,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase,
    private val insertMoviesListUseCase: InsertMoviesListUseCase,
    private val remoteFavoriteMovieUseCase: RemoteFavoriteMovieUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    sealed class Event {
        class TrendingSuccess(
            val trendingMoviesModel: List<TrendingMoviesModel>?,

            ) : Event()

        class CastSuccess(
            val cast: List<Cast>?
        ) : Event()

        class ActorSuccess(
            val actor: ActorModel?
        ) : Event()

        class TrailerSuccess(
            val trailerList: List<Result>?
        ) : Event()

        class DetailsSuccess(
            val details: MovieDetailsModel?
        ) : Event()

        class Failure(
            val localData: LiveData<List<TrendingMoviesModel>>?,
            val errorText: String
        ) : Event()

        object Loading : Event()

    }


    private val trendingMoviesChannel = Channel<Event>()
    val trendingMoviesFlow = trendingMoviesChannel.receiveAsFlow()

    private val castChannel = Channel<Event>()
    val castFlow = castChannel.receiveAsFlow()

    private val actorChannel = Channel<Event>()
    val actorFlow = actorChannel.receiveAsFlow()

    private val trailerChannel = Channel<Event>()
    val trailerFlow = trailerChannel.receiveAsFlow()

    private val detailsChannel = Channel<Event>()
    val detailsFlow = detailsChannel.receiveAsFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        viewModelScope.launch {
            trendingMoviesChannel.send(
                Event.Failure(
                    getAllMoviesUseCase.execute(),
                    ""
                )
            )
        }
    }

    fun getTrendingMovies() = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        if (isNetworkAvailable(context)) {
            trendingMoviesChannel.send(Event.Loading)
            when (val response = getTrendingMoviesUseCase.execute()) {
                is Resource.Success -> {
                    response.data?.let {
                        it.map { model ->
                            model.id?.let { id ->
                                val favModel = getFavoriteMovieByIdUseCase.execute(id)
                                model.isFavorite = favModel != null
                            }
                        }
                        deleteMoviesUseCase.execute()
                        trendingMoviesChannel.send(Event.TrendingSuccess(it))
                        insertMoviesListUseCase.execute(it)
                    } ?: kotlin.run {
                        trendingMoviesChannel.send(
                            Event.Failure(
                                getAllMoviesUseCase.execute(),
                                response.message ?: ""
                            )
                        )
                    }
                }
                is Resource.Error -> {
                    trendingMoviesChannel.send(
                        Event.Failure(
                            getAllMoviesUseCase.execute(),
                            response.message ?: ""
                        )
                    )
                }
            }
        } else {
            trendingMoviesChannel.send(
                Event.Failure(
                    getAllMoviesUseCase.execute(),
                    "No Internet" ?: ""
                )
            )
        }

    }

    fun getCredits(idMovie: Int) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            if (isNetworkAvailable(context)) {
                castChannel.send(Event.Loading)
                when (val response = getCreditsUseCase.execute(idMovie)) {
                    is Resource.Success -> {
                        response.data?.let {
                            castChannel.send(Event.CastSuccess(it))
                        } ?: kotlin.run {
                            castChannel.send(Event.Failure(null, response.message ?: ""))
                        }
                    }
                    is Resource.Error -> {
                        castChannel.send(Event.Failure(null, response.message ?: ""))
                    }
                }
            }

        }

    fun getPersonData(personId: Int) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            if (isNetworkAvailable(context)) {
                actorChannel.send(Event.Loading)
                when (val response = getPersonDataUseCase.execute(personId)) {
                    is Resource.Success -> {
                        response.data?.let {
                            actorChannel.send(Event.ActorSuccess(it))
                        } ?: kotlin.run {
                            actorChannel.send(Event.Failure(null, response.message ?: ""))
                        }
                    }
                    is Resource.Error -> {
                        actorChannel.send(Event.Failure(null, response.message ?: ""))
                    }
                }
            }
        }

    fun getMovieTrailer(movieId: Int) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            if (isNetworkAvailable(context)) {
                trailerChannel.send(Event.Loading)
                when (val response = getMovieTrailerUseCase.execute(movieId)) {
                    is Resource.Success -> {
                        response.data?.let {
                            trailerChannel.send(Event.TrailerSuccess(it))
                        } ?: kotlin.run {
                            trailerChannel.send(Event.Failure(null, response.message ?: ""))
                        }
                    }
                    is Resource.Error -> {
                        trailerChannel.send(Event.Failure(null, response.message ?: ""))
                    }
                }
            }
        }

    fun getMovieDetails(movieId: Int) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            if (isNetworkAvailable(context)) {
                when (val response = getMovieDetailsUseCase.execute(movieId)) {
                    is Resource.Success -> {
                        response.data?.let {
                            detailsChannel.send(Event.DetailsSuccess(it))
                        } ?: kotlin.run {
                            detailsChannel.send(Event.Failure(null, response.message ?: ""))
                        }
                    }
                    is Resource.Error -> {
                        detailsChannel.send(Event.Failure(null, response.message ?: ""))
                    }
                }
            }
        }

    fun updateMovieModel(movieModel: TrendingMoviesModel) =
        viewModelScope.launch(Dispatchers.IO) {
            updateFavoriteStatusUseCase.execute(movieModel)
        }

    fun getMovieList(): LiveData<List<TrendingMoviesModel>> = getAllMoviesUseCase.execute()

    fun getFavoriteMovies(): LiveData<List<FavouriteMovieModel>> = getFavoriteMoviesUseCase.execute()

    fun insertFavoriteMovie(favoriteMovieModel: FavouriteMovieModel) =
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteMovieUseCase.execute(favoriteMovieModel)
        }

    fun removeFavoriteMovie(favoriteMovieModel: FavouriteMovieModel) =
        viewModelScope.launch(Dispatchers.IO) {
            remoteFavoriteMovieUseCase.execute(favoriteMovieModel)
        }


}