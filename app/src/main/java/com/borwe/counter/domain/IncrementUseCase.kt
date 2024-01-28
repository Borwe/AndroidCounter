package com.borwe.counter.domain

import androidx.lifecycle.viewModelScope
import com.borwe.counter.CounterViewModel
import com.borwe.counter.events.Event
import kotlinx.coroutines.launch

class IncrementUseCase {
    fun invoke(counterVM: CounterViewModel){
        counterVM.viewModelScope.launch {
            counterVM.countEvents.value = Event.IncrementEvent()
        }
    }
}
