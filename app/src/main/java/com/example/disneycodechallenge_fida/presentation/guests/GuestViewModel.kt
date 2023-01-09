package com.example.disneycodechallenge_fida.presentation.guests

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.disneycodechallenge_fida.data.Guests

class GuestViewModel: ViewModel() {

    val hasReservationCount = mutableStateOf(0)
    val needReservationCount = mutableStateOf(0)
    val guestHasReservationCheck = mutableStateOf(false)
    val guestNeedReservationCheck = mutableStateOf(false)

    fun getGuestsList(): List<Guests>{
        return listOf(
            Guests("alpha", false, true),
            Guests("beta", false, true),
            Guests("gamma", false, true),
            Guests("delta", false, true),
            Guests("abc", false, false),
            Guests("def", false, false),
            Guests("ghi", false, false),
            Guests("jkl", false, false)
        )
    }

    fun hasReservation(isChecked: Boolean){
        if(isChecked){
            hasReservationCount.value++
            guestHasReservationCheck.value= true
        }
        else if(!isChecked && hasReservationCount.value>1){
            hasReservationCount.value--
            guestHasReservationCheck.value = true
        }
        else if(!isChecked && hasReservationCount.value<=1){
            hasReservationCount.value--
            guestHasReservationCheck.value = false
        }

    }

    fun needReservation(isChecked: Boolean){
        if(isChecked){
            needReservationCount.value++
            guestNeedReservationCheck.value = true
        }
        else if(!isChecked && needReservationCount.value>1){
            needReservationCount.value--
            guestNeedReservationCheck.value = true
        }
        else{
            needReservationCount.value--
            guestNeedReservationCheck.value = false
        }
    }

}