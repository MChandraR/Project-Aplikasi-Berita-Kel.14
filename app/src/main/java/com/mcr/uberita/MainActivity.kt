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
import com.mcr.uberita.util.colorPalettes

class MainActivity : ComponentActivity() {
    val context: Context = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainMenu()
        }

    }

    @Composable
    fun mainMenu(){
        UBeritaTheme(color = colorPalettes().BlueLight){
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = colorPalettes().BlueLight
            ) {
                Column(modifier = Modifier.padding(horizontal =  50.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Column(modifier = Modifier.fillMaxWidth().weight(.5f),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text="Let's start \nyour \njourney",color = colorPalettes().darkBlues, textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold, fontSize = 48.sp, lineHeight = 52.sp, fontFamily = FontFamily.SansSerif)
                    }
                    Column(modifier = Modifier.fillMaxWidth().weight(.3f), horizontalAlignment = Alignment.CenterHorizontally){
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(all=5.dp),colors =  ButtonDefaults.buttonColors(colorPalettes().yellow),
                            onClick = {
                                context.startActivity(Intent(context,SignInMenu::class.java))
                            }
                        ){
                            Text(text="Sign in", fontSize=16.sp,color = colorPalettes().darkBlues)
                        }
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(all=5.dp),
                            onClick = {
                                      context.startActivity(Intent(context,SignUpMenu::class.java))
                            },
                            colors =  ButtonDefaults.buttonColors(Color.White)){
                            Text(text="Sign up", fontSize=16.sp,color = colorPalettes().darkBlues)
                        }
                    }
                }

            }
        }
    }


    override fun onBackPressed() {
        finishAffinity()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}

