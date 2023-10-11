package com.example.wilber_p1_ap2.ui.theme.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val repository: CounterRepository
) : ViewModel() {

    val counter: Flow<Int> = repository.counter

    fun increment() {
        viewModelScope.launch {
            repository.increment()
        }
    }
}