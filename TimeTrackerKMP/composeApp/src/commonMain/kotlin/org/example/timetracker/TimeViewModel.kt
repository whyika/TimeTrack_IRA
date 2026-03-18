package org.example.timetracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.*

class TimeViewModel : ViewModel() {

    private val _timePassed = MutableStateFlow(calculateTimePassed())
    val timePassed: StateFlow<String> = _timePassed.asStateFlow()

    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                _timePassed.value = calculateTimePassed()
            }
        }
    }

    private fun calculateTimePassed(): String {
        val now = Clock.System.now()
        val timeZone = TimeZone.currentSystemDefault()
        val localDateTime = now.toLocalDateTime(timeZone)
        
        val startOfMonth = LocalDateTime(localDateTime.year, localDateTime.monthNumber, 1, 0, 0, 0, 0)
        val startOfMonthInstant = startOfMonth.toInstant(timeZone)
        
        val duration = now.minus(startOfMonthInstant)
        
        val days = duration.inWholeDays
        val hours = duration.inWholeHours % 24
        val minutes = duration.inWholeMinutes % 60
        val seconds = duration.inWholeSeconds % 60
        
        return "$days діб, $hours год, $minutes хв, $seconds сек"
    }
}
