package com.mantushnikita.disneyherocompose.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantushnikita.disneyherocompose.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    val state = MutableLiveData<ListScreenState>()

    fun processAction(action: ListScreenAction){
        when(action){
            is ListScreenAction.Init ->{
                loadListHeroes()
            }
        }
    }

    private fun loadListHeroes() {
        state.value = ListScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getHeroes()
            if (response.isSuccessful) {
                val heroList =response.body()?.data
                if (heroList != null){
                    state.postValue(ListScreenState.Loaded(heroList))
                }
                else{
                    state.postValue(ListScreenState.Error("Hero list no found"))
                }
            }
            else{
                state.postValue(ListScreenState.Error("Network error"))
            }
        }
    }
}