package com.demo.reposData.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.reposData.common.Resource
import com.demo.reposData.data.model.JackwhartonResponseItem
import com.demo.reposData.domain.model.ReposDataUI
import com.demo.reposData.domain.usecase.repos.GetReposDataUseCase
import com.demo.reposData.domain.usecase.repos.InsertReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getReposDataUseCase: GetReposDataUseCase,
    private val getReposFromLocalUseCase: InsertReposUseCase
) : ViewModel() {

    private val _reposDataOfflineFlow = MutableStateFlow<Resource<List<ReposDataUI>>>(Resource.Loading)
    val reposDataOfflineFlow = _reposDataOfflineFlow.asStateFlow()

    private val _reposDataFirstFlow = MutableStateFlow<Resource<List<JackwhartonResponseItem>>>(Resource.Loading)
    val reposDataFirstFlow = _reposDataFirstFlow.asStateFlow()

    private val _reposDataNextFlow = MutableStateFlow<Resource<List<JackwhartonResponseItem>>>(Resource.Loading)
    val reposDataNextFlow = _reposDataNextFlow.asStateFlow()

    fun reposDataFirstPage(page : Int) = viewModelScope.launch {
        getReposDataUseCase.invoke(page).collect { result ->
            _reposDataFirstFlow.emit(result)
        }
    }


    fun reposDataNextPage(page : Int) = viewModelScope.launch {
        getReposDataUseCase.invoke(page).collect { result ->
            _reposDataNextFlow.emit(result)
        }
    }

    fun getDataFromOffline() = viewModelScope.launch {
        getReposFromLocalUseCase.invoke().collect { result ->
            _reposDataOfflineFlow.emit(result)
        }
    }



}