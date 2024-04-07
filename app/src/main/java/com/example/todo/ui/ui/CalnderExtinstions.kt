package com.example.todo.ui.ui

import java.text.SimpleDateFormat
import java.util.Calendar

fun Calendar.formatTime() : String{
        val format = SimpleDateFormat("h:mm a")
        return format.format(time)
}

fun Calendar.formatDate() : String{
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(time)
}

fun Calendar.getDateOnly() : Long{
    val calendar = Calendar.getInstance()
    calendar.time = this.time
    calendar.set(this.get(Calendar.YEAR),this.get(Calendar.MONTH),this.get(Calendar.DATE),0,0,0)
    calendar.set(Calendar.MILLISECOND,0)
    return calendar.timeInMillis
}

fun Calendar.getTimeOnly() : Long{
    val calendar = Calendar.getInstance()
    calendar.time = this.time
    calendar.set(this.get(Calendar.HOUR),this.get(Calendar.MINUTE),this.get(Calendar.MILLISECOND))
    calendar.set(Calendar.MILLISECOND,0)
    return calendar.timeInMillis
}