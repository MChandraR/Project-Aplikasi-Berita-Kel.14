package com.mcr.uberita

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mcr.uberita.interfaces.userAPI
import com.mcr.uberita.model.loginModel
import com.mcr.uberita.ui.theme.UBeritaTheme
import com.mcr.uberita.util.clientAPI
import com.mcr.uberita.util.colorPalettes
import com.mcr.uberita.util.myCustomUI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SignInMenu : ComponentActivity() {
    var context: Context = this
    var btnEnabled : MutableState<Boolean> = mutableStateOf(true)
    val myCustomUI:myCustomUI = myCustomUI(context)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainMenu()
        }
        context = this
    }

    fun signIn(context: Context,username:String, password:String){
            var SP :SharedPreferences.Editor = getSharedPreferences("mcr",Context.MODE_PRIVATE).edit()
            val clientAPI:Retrofit = clientAPI().getClientAPI()
            var userAPI:userAPI = clientAPI.create(userAPI::class.java)
            var request  = userAPI.signIn(loginModel(username,password))

            request.enqueue(object:retrofit2.Callback<loginModel>{
                override fun onResponse(call: Call<loginModel>, response: Response<loginModel>) {
                    var resData = response.body()!!
                    if(resData.status=="sukses"){
                        Toast.makeText(context,"Login berhasil userID : "+resData.data.user_id,Toast.LENGTH_SHORT).show()
                        startActivity(Intent(context, MainMenu::class.java))
                        SP.putString("user_id",resData.data.user_id)
                        SP.putString("username",resData.data.username)
                        SP.putString("email",resData.data.email)
                        SP.commit()
                    }else{
                        Toast.makeText(context,resData.message,Toast.LENGTH_SHORT).show()
                    }
                    btnEnabled.value = true
                }

                override fun onFailure(call: Call<loginModel>, t: Throwable) {
                    btnEnabled.value = true
                }

            })


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun mainMenu(){
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        UBeritaTheme(color = colorPalettes().darkBlues) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = colorPalettes().darkBlues
            ) {
                Column(modifier = Modifier.padding(horizontal =  50.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(.5f),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Text(modifier = Modifier
                            .clickable {
                                startActivity(Intent(context, MainMenu::class.java))
                            },
                            text="Welcome back", color = Color.White,textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold, fontSize = 48.sp, lineHeight = 52.sp, fontFamily = FontFamily.SansSerif)
                    }
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(.5f), horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text="Email", textAlign = TextAlign.Left, color = Color.White, modifier = Modifier.fillMaxWidth())
                        myCustomUI.myTextField(value = email ,
                            onValueChange = {email = it},
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Black,
                                disabledTextColor = colorPalettes().invisibles,
                                focusedIndicatorColor =colorPalettes().BlueLight,
                                unfocusedIndicatorColor = colorPalettes().invisibles,
                                disabledIndicatorColor = colorPalettes().invisibles,
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(50, 50, 50, 50),
                            modifier  = Modifier
                                .padding(bottom = 10.dp)
                                .background(colorPalettes().invisibles),
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.baseline_person_outline_24), contentDescription = "Password")
                            },
                            placeholder = {
                                myCustomUI.placeHolder("Masukkan email")
                            })


                        Text(text="Password", textAlign = TextAlign.Left, color = Color.White,modifier = Modifier.fillMaxWidth())
                        myCustomUI.myTextField(value = password ,
                            onValueChange = {password = it},
                            passwordVisible = false,
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Black,
                                disabledTextColor = colorPalettes().invisibles,
                                focusedIndicatorColor =colorPalettes().BlueLight,
                                unfocusedIndicatorColor = colorPalettes().invisibles,
                                disabledIndicatorColor = colorPalettes().invisibles,
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(25, 25, 25, 25),
                            modifier  = Modifier
                                .padding(bottom = 30.dp)
                                .background(colorPalettes().invisibles),
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.baseline_vpn_key_24), contentDescription = "Password")
                            },
                            placeholder = {
                                myCustomUI.placeHolder("Masukkan password")
                            })
                        Button(
                            enabled = btnEnabled.value ,
                            modifier = Modifier.fillMaxWidth(),colors =  ButtonDefaults.buttonColors(
                                colorPalettes().yellow),
                            onClick = {
                                btnEnabled.value = false
                                signIn(context,email,password)
                            },
                            shape = RoundedCornerShape(20,20,20,20)
                        ){
                            Text(text="Login", color = colorPalettes().darkBlues)
                        }

                        Text(text = "Don't have an account yet ?", color = Color.White, fontSize = 13.sp)
                        TextButton(
                            onClick = {
                                startActivity(Intent(context, SignUpMenu::class.java))
                            },
                            modifier = Modifier.offset(y=-13.dp)
                        ) {
                            Text(text = "Sign up", color = Color.Yellow,fontSize = 13.sp)
                        }

                    }
                }

            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(context,MainActivity::class.java))
    }
}


