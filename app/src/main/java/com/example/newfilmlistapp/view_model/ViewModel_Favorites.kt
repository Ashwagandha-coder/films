package com.example.newfilmlistapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfilmlistapp.model.ResultPopular
import com.example.newfilmlistapp.repository.RepositoryForRoom
import kotlinx.coroutines.launch

class ViewModel_Favorites(private val repositoryForRoom: RepositoryForRoom) : ViewModel() {

    private val mutableLiveData_favorite: MutableLiveData<ResultPopular> = MutableLiveData()
    val favorite = mutableLiveData_favorite


    private fun loadData() {

        viewModelScope.launch {



        }


    }


}