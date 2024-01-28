package com.borwe.counter.events

sealed class Event {
    class IncrementEvent(): Event()
    class DecrementEvent(): Event()
}