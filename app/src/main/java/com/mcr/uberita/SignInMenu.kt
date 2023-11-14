package com.mcr.uberita

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mcr.uberita.ui.theme.UBeritaTheme
import com.mcr.uberita.util.colorPalette

class SignInMenu : ComponentActivity() {
    val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainMenu()
        }
    }

    @Composable
    fun mainMenu(){
        UBeritaTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = colorPalette().darkBlue
            ) {
                Column(modifier = Modifier.padding(horizontal =  50.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(.5f),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text="Wellcome back", color = Color.White,textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold, fontSize = 48.sp, lineHeight = 52.sp, fontFamily = FontFamily.SansSerif)
                    }
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(.3f), horizontalAlignment = Alignment.CenterHorizontally){
                        Button(
                            modifier = Modifier.fillMaxWidth(),colors =  ButtonDefaults.buttonColors(
                                colorPalette().yellow),
                            onClick = {
                            }
                        ){
                            Text(text="Login")
                        }

                        Text(text = "Don't have an account yet ?", color = Color.White)
                        Text(text = "Sign up", color = Color.Yellow)

                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    UBeritaTheme {
        Greeting("Android")
    }
}