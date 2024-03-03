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
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    val hero = MutableLiveData<Hero>()

    fun getHeroById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getHeroById(id)
            if (response.isSuccessful){
                response.body()?.data?.toHero().let{
                    hero.postValue(it)
                }
            }
        }
    }
}