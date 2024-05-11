package com.sedogapps.gidertablom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedogapps.gidertablom.model.CategoryListview
import com.sedogapps.gidertablom.repository.HomePageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val homePageRepository: HomePageRepository) : ViewModel() {
    private val _expensesWithCategories = MutableLiveData<List<CategoryListview>>()
    val expensesWithCategoriesList: LiveData<List<CategoryListview>> get() = _expensesWithCategories

    private val _isSuccesful = MutableLiveData<Boolean>()
    val isSuccesful: LiveData<Boolean> get() = _isSuccesful

    fun getAllList() {

        viewModelScope.launch(Dispatchers.IO) {
            homePageRepository.getAllExpenses().collect { allExpenses ->
                println("viewmodel girdi")

                withContext(Dispatchers.Main) {
                    _expensesWithCategories.value = allExpenses

                }
            }
        }

//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val expensesWithCategories = homePageRepository.getAllExpenses()
//                    .toList() // Flow'ı bir liste olarak toplar
//                withContext(Dispatchers.Main) {
//                    _expensesWithCategories.value = expensesWithCategories
//                    _isSuccesful.postValue(true)
//                }
//            } catch (e: Exception) {
//                println("viewmodel hata")
//                _isSuccesful.postValue(false)
//            }
//        }
    }


}