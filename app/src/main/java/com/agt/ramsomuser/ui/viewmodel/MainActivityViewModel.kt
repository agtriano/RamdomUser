package com.agt.ramsomuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agt.ramsomuser.data.model.Result
import com.agt.ramsomuser.data.model.Results
import com.agt.ramsomuser.domain.GetAllContactsUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel(){
    val allConstactsListModel = MutableLiveData<Results>()
    var getAllContactsListUseCase = GetAllContactsUseCase()
    val contactSelected = MutableLiveData<Result>()

    fun onCreate() {
        viewModelScope.launch {

            val result = getAllContactsListUseCase()

            if (result != null) {
                if (!result.results.isEmpty()) {
                    allConstactsListModel.postValue(result)
                }
            }
        }
    }

    private fun tolog(s: String) {
        Log.d("RAMDOMUSER",s)
    }

    fun onContactSelected(it: Int) {
        contactSelected.postValue(allConstactsListModel.value?.results?.get(it))
    }


}
