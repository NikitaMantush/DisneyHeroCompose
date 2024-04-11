package com.mantushnikita.disneyherocompose.ui.hero

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantushnikita.disneyherocompose.model.Hero
import com.mantushnikita.disneyherocompose.repository.HeroRepository
import com.mantushnikita.disneyherocompose.util.toHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    val state = MutableLiveData<HeroState>()

    //var isFavorite = false

    private var currentId: Int? = null

    fun processAction(action: HeroAction) {
        when (action) {
            is HeroAction.LoadById -> {
                getHeroById(action.heroId)
            }
            is HeroAction.Reload -> {
                currentId?.let { heroId ->
                    getHeroById(heroId)
                }
            }
        }
    }

    private fun getHeroById(id: Int) {
        state.value = HeroState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getHeroById(id)
            if (response.isSuccessful) {
                val hero = response.body()?.data?.toHero()
                if (hero != null) {
                    val isFavorite = repository.isHeroFavorite(hero._id)
                    hero.isFavorite = isFavorite
                    state.postValue(HeroState.HeroLoaded(hero))
                    currentId = id

                } else {
                    state.postValue(HeroState.Error("Hero not found"))
                }
            } else {
                state.postValue(HeroState.Error("Network Error"))
            }
        }
    }

    fun addHero(hero: Hero) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(hero)
        }

    }
    fun deleteHero(heroId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHero(heroId)
        }
    }
}