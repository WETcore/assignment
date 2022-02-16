package com.aqua.snapask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aqua.snapask.data.Api
import com.aqua.snapask.data.GithubUser
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {
    private val _users = MutableLiveData<List<GithubUser>>()
    val users: LiveData<List<GithubUser>>
        get() = _users

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getUsers()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun getUsers() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _users.postValue(Api.retrofitService.getUser())
                } catch (e: Exception) {
                    Log.d("TEST","${e.message}")
                }
            }
        }
    }
}