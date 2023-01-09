package com.example.disneycodechallenge_fida

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.disneycodechallenge_fida.presentation.guests.ConfirmationScreen
import com.example.disneycodechallenge_fida.presentation.guests.SelectGuestScreen
import com.example.disneycodechallenge_fida.ui.theme.DisneyCodeChallenge_FidaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisneyCodeChallenge_FidaTheme {

                Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "App", modifier = Modifier.fillMaxWidth().semantics { heading() }.padding(8.dp))},
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                backgroundColor = MaterialTheme.colors.background
            )
        },
        content = { SelectGuestPage() }
                )

                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//
//                }
            }
        }
    }
}

@Composable
fun SelectGuestPage() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "SelectGuestScreen"){
        composable("SelectGuestScreen"){
            SelectGuestScreen(navController)
        }
        composable("ConfirmationScreen"){
            ConfirmationScreen()
        }
    }
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "App")},
//                navigationIcon = {
//                    IconButton(onClick = {}) {
//                        Icon(Icons.Filled.ArrowBack, "Navigate up")
//                    }
//                },
//                backgroundColor = MaterialTheme.colors.background
//            )
//        },
//        content = { SelectGuestScreen() } )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DisneyCodeChallenge_FidaTheme {
        Greeting("Android")
    }
}