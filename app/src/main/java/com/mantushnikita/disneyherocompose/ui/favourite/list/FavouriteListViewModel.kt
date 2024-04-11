package com.mantushnikita.disneyherocompose.ui.favourite.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantushnikita.disneyherocompose.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteListViewModel @Inject constructor(
    private val repository: HeroRepository
): ViewModel() {

    val state = MutableLiveData<FavouriteListState>()

    fun processAction(action: FavouriteListAction){
        when(action){
            is FavouriteListAction.Init -> {
                loadFavouriteList()
            }
        }
    }

    private fun loadFavouriteList() {
        state.value = FavouriteListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getHeroList()
                state.postValue(FavouriteListState.Loaded(list))
        }
    }
}