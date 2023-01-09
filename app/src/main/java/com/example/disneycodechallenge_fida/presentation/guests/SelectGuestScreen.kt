package com.example.disneycodechallenge_fida.presentation.guests

import android.util.Log
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
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.disneycodechallenge_fida.data.Guests
import kotlinx.coroutines.launch

@Composable
fun Guest(
    guest: Guests,
    viewModel: GuestViewModel,
    checked: Boolean
) {
    val isChecked = rememberSaveable{ mutableStateOf(checked) }

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    isChecked.value = !isChecked.value
                    //onCheckedChange(!checked)
                    guest.isSelected = !guest.isSelected
                    if (guest.hasReservation)
                        viewModel.hasReservation(isChecked.value)
                    else
                        viewModel.needReservation(isChecked.value)
                },
                onClickLabel = "${if(isChecked.value) "uncheck" else "check"} ${guest.name} Checkbox, it is ${if(isChecked.value) "checked" else "not checked"}"
            )
            .clip(MaterialTheme.shapes.small)
            .requiredHeight(ButtonDefaults.MinHeight)
            .padding(8.dp)
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
    viewModel: GuestViewModel,
    ) {
    Column() {
        names.forEach {
            Guest(guest = it, viewModel,false)
        }
    }
}

@Composable
fun SelectGuests(
    modifier: Modifier = Modifier,
    viewModel: GuestViewModel,
    guests:List<Guests>,
    ) {
    Column(modifier = modifier
        .verticalScroll(state = rememberScrollState())
    ) {
        
        Text(text = "These Guests Have Reservations", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().semantics { heading() }.padding(8.dp))
        Guests(names = guests.filter{
            it.hasReservation
        },viewModel)
        //Guests(names = listOf("alpha", "beta" , "gamma","alpha", "beta" , "gamma","alpha", "beta" , "gamma","alpha", "beta" , "gamma"))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "These Guests Need Reservations", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().semantics { heading() }.padding(8.dp))
        Guests(names = guests.filter {
            !it.hasReservation
        },viewModel)
        Row(Modifier.semantics(mergeDescendants = true) {  }.padding(8.dp)) {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Info icon", modifier = Modifier
                .size(20.dp)
                .padding(3.dp))
            Text(fontSize = 12.sp,text = "At least one Guest in the party must have a reservation. Guests without reservations must remain in the same booking party in order to enter")
        }
    }
}

@Composable
fun SelectGuestScreen(navController: NavController,
                      viewModel: GuestViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
   
    val guests = viewModel.getGuestsList()

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember {SnackbarHostState() }
    val scaffoldState = rememberScaffoldState()
    Scaffold(snackbarHost = {SnackbarHost(hostState = snackbarHostState)}) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            SelectGuests(modifier = Modifier.weight(1f), viewModel, guests)
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                onClick = {
                    if (viewModel.guestHasReservationCheck.value && viewModel.guestNeedReservationCheck.value)
                        Log.d("buttonclick", "Conflict")
                    else if (viewModel.guestHasReservationCheck.value)
                        navController.navigate("ConfirmationScreen")
                        //Log.d("buttonclick", " Proceed ")
                    else
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Reservation Needed\nSelect at least one Guest that has a reservation to continue")
                        }
                },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50),
                enabled = viewModel.guestHasReservationCheck.value || viewModel.guestNeedReservationCheck.value
            ) {
                Text(text = "Continue")
            }
        }
    }
}

