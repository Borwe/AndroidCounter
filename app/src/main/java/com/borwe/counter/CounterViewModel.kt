package com.borwe.counter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borwe.counter.domain.DecrementUseCase
import com.borwe.counter.domain.IncrementUseCase
import com.borwe.counter.events.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CounterViewModel private constructor(val increment: IncrementUseCase, val decrement: DecrementUseCase): ViewModel() {

    val countEvents = MutableLiveData<Event>()
    val _count = MutableStateFlow(0)
    val counterValue = _count.asStateFlow()

    companion object {
        fun create(viewLifecycleOwner: LifecycleOwner, increment: IncrementUseCase, decrement: DecrementUseCase): CounterViewModel {
            var viewModel = CounterViewModel(increment, decrement)
            viewModel.countEvents.observe(viewLifecycleOwner){
                when(it){
                    is Event.IncrementEvent-> viewModel._count.value += 1
                    is Event.DecrementEvent-> viewModel._count.value -= 1
                }
            }
            return viewModel
        }
    }
}
