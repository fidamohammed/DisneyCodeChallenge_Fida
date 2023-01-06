package com.example.disneycodechallenge_fida

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
                            title = { Text(text = "App")},
                            navigationIcon = {
                                IconButton(onClick = {}) {
                                    Icon(Icons.Filled.ArrowBack, "backIcon")
                                }
                            },
                            backgroundColor = MaterialTheme.colors.background
                        )
                             },
                    content = { SelectGuestScreen() } )
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    SelectGuestScreen(){
//
//                    }
//                }
            }
        }
    }
}

@Composable
fun Home() {
    Column() {
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            onClick = {

            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "Guests")
        }
    }

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