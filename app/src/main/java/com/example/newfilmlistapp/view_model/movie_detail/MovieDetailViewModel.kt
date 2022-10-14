package com.example.newfilmlistapp.view_model.movie_detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.local.db.AppDatabase
import com.example.newfilmlistapp.model.MovieDetailWrapper
import com.example.newfilmlistapp.model.MovieDetailWrapperRoom
import com.example.newfilmlistapp.repository.RepositoryAPI
import com.example.newfilmlistapp.view_model.movie_recomendation.MovieRecomendationViewModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repositoryAPI: RepositoryAPI): ViewModel() {

    private val mutableLiveDataMovieDetail: MutableLiveData<MovieDetailWrapper> = MutableLiveData()
    val movieDetailWrapper: LiveData<MovieDetailWrapper> = mutableLiveDataMovieDetail


    fun onLoad(movie: MovieDetailWrapperRoom, context: Context) {

        viewModelScope.launch {

            try {

                AppDatabase.getDatabase(context).movieDao().insert(movie)
                Log.d(MovieDetailViewModel::class.java.name, "request OK - insert in DB")

            } catch (e: Exception) {
                Log.e(MovieDetailViewModel::class.java.name, "Error request - insert in DB")
                e.printStackTrace()
            }

        }

    }

    fun onDelete(movie: MovieDetailWrapperRoom,context: Context) {

        viewModelScope.launch {

            try {

                AppDatabase.getDatabase(context).movieDao().deleteMovie(movie)
                Log.d(MovieDetailViewModel::class.java.name, "request OK - delete from DB")

            } catch (e: Exception) {
                Log.d(MovieDetailViewModel::class.java.name, "Error request - delete from DB")
                e.printStackTrace()
            }

        }



    }



    fun requestMovieDetail(id: Int) {

        viewModelScope.launch {

            try {

                // Movie Detail
                Log.d(MovieDetailViewModel::class.java.name,"Значение movieID before request - $id")

                mutableLiveDataMovieDetail.value = repositoryAPI.getMovieDetail(id.toString())

            }

            catch (e: Exception) {
                Log.d(MovieRecomendationViewModel::class.java.name,"Значение movieID before request with error - $id")
                Log.d(MovieRecomendationViewModel::class.java.name,"Error Request -  Movie Detail")
                e.printStackTrace()
            }


        }

    }


    fun searchInDb(id_movie: Int): Boolean {

        var flag: Boolean = false

        viewModelScope.launch {

            val data = repositoryRoom.getMovieListLocal()

            data?.forEach {
                if (it.id.toInt() == id_movie)
                    flag = true
                else
                    flag = false
            }

        }

        return flag

    }


}