package com.mcr.uberita

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mcr.uberita.ui.theme.UBeritaTheme
import com.mcr.uberita.util.colorPalette

class SignUpMenu : ComponentActivity() {
    val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpView()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SignUpView(){
        UBeritaTheme(colorPalette().blue) {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = colorPalette().blue
            ) {
                var email by remember {
                    mutableStateOf("")
                }
                var username by remember {
                    mutableStateOf("")
                }
                var password by remember {
                    mutableStateOf("")
                }
                Column(modifier = Modifier.padding(horizontal =  50.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(.4f),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text="Create Account", color = Color.White,textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold, fontSize = 48.sp, lineHeight = 52.sp, fontFamily = FontFamily.SansSerif)
                    }
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(.6f), horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text="Email", textAlign = TextAlign.Left, color = Color.White, modifier = Modifier.fillMaxWidth())
                        myTextField(value = email ,
                            onValueChange = {email = it},
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Black,
                                disabledTextColor = colorPalette().invisible,
                                focusedIndicatorColor =colorPalette().BlueLight,
                                unfocusedIndicatorColor = colorPalette().invisible,
                                disabledIndicatorColor = colorPalette().invisible,
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(50, 50, 50, 50),
                            modifier  = Modifier
                                .padding(bottom = 10.dp)
                                .background(colorPalette().invisible),
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.baseline_email_24), contentDescription = "Password")
                            },
                            placeholder = {
                                placeHolder("Masukkan email")
                            })

                        Text(text="Username", textAlign = TextAlign.Left, color = Color.White, modifier = Modifier.fillMaxWidth())
                        myTextField(value = username ,
                            onValueChange = {username = it},
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Black,
                                disabledTextColor = colorPalette().invisible,
                                focusedIndicatorColor =colorPalette().BlueLight,
                                unfocusedIndicatorColor = colorPalette().invisible,
                                disabledIndicatorColor = colorPalette().invisible,
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(50, 50, 50, 50),
                            modifier  = Modifier
                                .padding(bottom = 10.dp)
                                .background(colorPalette().invisible),
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.baseline_person_outline_24), contentDescription = "Password")
                            },
                            placeholder = {
                                placeHolder("Masukkan username")
                            })


                        Text(text="Password", textAlign = TextAlign.Left, color = Color.White,modifier = Modifier.fillMaxWidth())
                        myTextField(value = password ,
                            onValueChange = {password = it},
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Black,
                                disabledTextColor = colorPalette().invisible,
                                focusedIndicatorColor =colorPalette().BlueLight,
                                unfocusedIndicatorColor = colorPalette().invisible,
                                disabledIndicatorColor = colorPalette().invisible,
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(25, 25, 25, 25),
                            modifier  = Modifier
                                .padding(bottom = 30.dp)
                                .background(colorPalette().invisible),
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.baseline_vpn_key_24), contentDescription = "Password")
                            },
                            placeholder = {
                                placeHolder("Masukkan password")
                            })


                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp),colors =  ButtonDefaults.buttonColors(
                                colorPalette().yellow),
                            onClick = {
                                Toast.makeText(context, "Username : " + email + "\nPassword  : "+ password, Toast.LENGTH_SHORT).show()
                            },
                            shape = RoundedCornerShape(20,20,20,20)
                        ){
                            Text(text="Create Account",color = colorPalette().darkBlue)
                        }

                        Text(text = "Already have an account  ?", color = Color.White, fontSize = 13.sp)

                        TextButton(
                            onClick = {
                            startActivity(Intent(context, SignInMenu::class.java))
                            },
                            modifier = Modifier.offset(y=-13.dp)
                        ) {
                            Text(text = "Sign in", color = Color.Yellow,fontSize = 13.sp)
                        }

                    }
                }
            }
        }
    }


    override fun onBackPressed() {
        startActivity(Intent(context,MainActivity::class.java))
    }
}
