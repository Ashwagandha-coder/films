package com.example.newfilmlistapp.repository

import com.example.newfilmlistapp.model.ResultPopular

interface RepositoryForRoom {

    suspend fun insertDB()

    suspend fun updateDB()

    suspend fun getMovieListLocal(): List<ResultPopular>?

    suspend fun getMovieLocal(id: String): ResultPopular?

    suspend fun insertLocal(movie: ResultPopular)

    suspend fun insertLocal(list: List<ResultPopular>)

    suspend fun updateLocal(movie: ResultPopular)

    suspend fun deleteMovieLocal(movie: ResultPopular)

    suspend fun deleteMovieLocal(id: String)


}