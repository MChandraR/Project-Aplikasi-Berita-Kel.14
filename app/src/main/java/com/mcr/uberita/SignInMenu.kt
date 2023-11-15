package com.mcr.uberita

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun mainMenu(){
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        UBeritaTheme(color = colorPalette().darkBlue) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = colorPalette().darkBlue
            ) {
                Column(modifier = Modifier.padding(horizontal =  50.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(.5f),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text="Welcome back", color = Color.White,textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold, fontSize = 48.sp, lineHeight = 52.sp, fontFamily = FontFamily.SansSerif)
                    }
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(.5f), horizontalAlignment = Alignment.CenterHorizontally){
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
                                Icon(painter = painterResource(id = R.drawable.baseline_person_outline_24), contentDescription = "Password")
                            },
                            placeholder = {
                                placeHolder("Masukkan email")
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
                            modifier = Modifier.fillMaxWidth(),colors =  ButtonDefaults.buttonColors(
                                colorPalette().yellow),
                            onClick = {
                                      Toast.makeText(context, "Username : " + email + "\nPassword  : "+ password, Toast.LENGTH_SHORT).show()
                            },
                            shape = RoundedCornerShape(20,20,20,20)
                        ){
                            Text(text="Login")
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
        startActivity(Intent(context,MainActivity::class.java))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myTextField(
    leadingIcon:  @Composable (() -> Unit)? = null,
    trailingIcon:  @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    value:String = "",
    modifier:Modifier,
    onValueChange: (String) -> Unit,
    colors:androidx.compose.material3.TextFieldColors = TextFieldDefaults.textFieldColors(colorPalette().invisible),
    shape: Shape = TextFieldDefaults.filledShape
){
    val mergedTextStyle = LocalTextStyle.current.merge(androidx.compose.ui.text.TextStyle(color = Color.Black))
    BasicTextField(
        value = value,
        modifier = modifier
            .defaultMinSize(
                minHeight = 16.dp
            ).background(colorPalette().invisible)
            .fillMaxWidth()
            .padding(all = 0.dp),
        onValueChange = onValueChange,
        enabled = true,
        readOnly = false,
        cursorBrush = SolidColor(colorPalette().BlueLight),
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
        interactionSource = remember { MutableInteractionSource() },
        singleLine = true,
        maxLines = 1,
        decorationBox = {
            TextFieldDefaults.TextFieldDecorationBox(
                contentPadding = PaddingValues(0.dp) ,
                value = value,
                visualTransformation = VisualTransformation.None,
                innerTextField = it,
                placeholder = placeholder,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                supportingText = supportingText,
                shape = RoundedCornerShape(50,50,50,50),
                singleLine = true,
                enabled = true,
                isError = false,
                interactionSource = remember { MutableInteractionSource() },
                colors = colors
            )
        }

    )
}

@Composable
fun placeHolder(text:String=""){
    Text(text = text, color = colorPalette().gray, fontSize = 14.sp)
}
