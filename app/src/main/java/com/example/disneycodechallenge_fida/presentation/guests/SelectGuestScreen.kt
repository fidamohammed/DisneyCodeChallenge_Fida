package com.example.disneycodechallenge_fida.presentation.guests

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disneycodechallenge_fida.data.Guests

@Composable
fun Guest(
    guest: Guests,
    checked: Boolean,
    isAtleastOneSelected: MutableState<Boolean>,
    hasReservationCount: MutableState<Int>,
    enableContinue: MutableState<Boolean>,
    onCheckedChange: ((Boolean) -> Unit)
) {
    val isChecked = rememberSaveable{ mutableStateOf(checked) }

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(
                onClick = {
                    isChecked.value = !isChecked.value
                    //onCheckedChange(!checked)
                    guest.isSelected = !guest.isSelected
                    if (guest.hasReservation){
                        if(guest.isSelected)
                            hasReservationCount.value++
                        else
                            hasReservationCount.value--
                        isAtleastOneSelected.value = hasReservationCount.value>0
                    }

                    Log.d("proceed", hasReservationCount.value.toString())
                    Log.d("proceed", guest.toString())
//                    enableContinue.value = guest.isSelected
//                    Log.d("proceed",enableContinue.value.toString())
                }
            )
            .clip(MaterialTheme.shapes.small)
            .requiredHeight(ButtonDefaults.MinHeight)
            .padding(4.dp)
            //.offset(x = (-16).dp)
    ) {


        Checkbox(
            checked = isChecked.value,
            onCheckedChange = null,
//            {
//                isChecked.value = it
//                              },
            colors = CheckboxDefaults.colors(Color.Green)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = guest.name)
    }
}

@Composable
fun Guests(
    names: List<Guests>,
    isAtleastOneSelected: MutableState<Boolean>,
    enableContinue: MutableState<Boolean>) {
    val hasReservationCount = rememberSaveable {
        mutableStateOf(0)
    }
    Column() {
        names.forEach {
            Guest(guest = it, false,isAtleastOneSelected,hasReservationCount,enableContinue){

            }
        }
    }
}

@Composable
fun SelectGuests(
    modifier: Modifier = Modifier,
    guests:List<Guests>,
    isAtleastOneSelected: MutableState<Boolean>,
    enableContinue: MutableState<Boolean>) {
    Column(modifier = modifier
        .verticalScroll(state = rememberScrollState())
    ) {
        val reservedGuests = guests.filter{
            it.hasReservation
        }
        val needReservationGuests = guests.filter {
            !it.hasReservation
        }
        Text(text = "These Guests Have Reservations", fontWeight = FontWeight.Bold)
        Guests(names = guests.filter{
            it.hasReservation
        },isAtleastOneSelected,enableContinue)
        //Guests(names = listOf("alpha", "beta" , "gamma","alpha", "beta" , "gamma","alpha", "beta" , "gamma","alpha", "beta" , "gamma"))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "These Guests Need Reservations", fontWeight = FontWeight.Bold)
        Guests(names = guests.filter {
            !it.hasReservation
        },isAtleastOneSelected,enableContinue)
        Row() {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Info icon", modifier = Modifier
                .size(20.dp)
                .padding(3.dp))
            Text(fontSize = 12.sp,text = "At least one Guest in the party must have a reservation. Guests without reservations must remain in the same booking party in order to enter")
        }
    }
}

@Composable
fun SelectGuestScreen() {
    val isAtleastOneSelected = rememberSaveable {
        mutableStateOf(false)
    }
    val guests = listOf(
        Guests("alpha", false, true),
        Guests("beta", false, true),
        Guests("gamma", false, true),
        Guests("delta", false, true),
        Guests("abc", false, false),
        Guests("def", false, false),
        Guests("ghi", false, false),
        Guests("jkl", false, false)
    )

    val enableContinue = rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    //Scaffold(scaffoldState = scaffoldState) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        SelectGuests(modifier = Modifier.weight(1f), guests, isAtleastOneSelected, enableContinue)
        Spacer(modifier = Modifier.height(10.dp))



        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            onClick = {
                if (isAtleastOneSelected.value) {
                    Log.d("proceed", "Ok to proceed")
                } else {
//                    coroutineScope.launch {
//                        scaffoldState.snackbarHostState.showSnackbar("Reservation Needed\n" +
//                                "Select at least one Guest that has a reservation to continue")
//                    }
                    Toast.makeText(context,"Reservation Needed\nSelect at least one Guest that has a reservation to continue",
                        Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50),
            enabled = true
        ) {
            Text(text = "Continue")
        }
    }
    // }
}

