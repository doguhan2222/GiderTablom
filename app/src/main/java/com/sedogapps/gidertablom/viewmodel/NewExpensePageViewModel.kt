package com.sedogapps.gidertablom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedogapps.gidertablom.repository.NewExpensePageRepository
import com.sedogapps.gidertablom.room.entities.Categories
import com.sedogapps.gidertablom.room.entities.Expenses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class NewExpensePageViewModel @Inject constructor(private val newExpensePageRepository: NewExpensePageRepository) : ViewModel() {
    private val _categories = MutableLiveData<List<Categories>>()
    val categoryList: LiveData<List<Categories>> get() = _categories

    private val _isSuccesful = MutableLiveData<Boolean>()
    val isSuccesful: LiveData<Boolean> get() = _isSuccesful

    fun getCategories(){

        viewModelScope.launch(Dispatchers.IO) {
            newExpensePageRepository.getAllCategories().collect { categoryList ->
                withContext(Dispatchers.Main) {
                    _categories.value = categoryList
                }
            }
        }

    }

    fun saveNewExpense(newExpense:Expenses){
        viewModelScope.launch ( Dispatchers.IO){

            val isSaved:Boolean = newExpensePageRepository.insertNewExpense(newExpense)
            withContext(Dispatchers.Main){
                _isSuccesful.value = isSaved
            }
        }
    }

    fun setIsSuccessful(isSuccesful:Boolean){
        this._isSuccesful.value = isSuccesful
    }



}