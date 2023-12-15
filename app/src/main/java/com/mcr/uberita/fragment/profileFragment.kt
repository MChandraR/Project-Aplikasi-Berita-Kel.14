package com.mcr.uberita.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Looper
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mcr.uberita.R
import com.mcr.uberita.util.colorPalettes
import com.mcr.uberita.util.myCustomUI

class profileFragment(val context: Context) {
    var showPicture:MutableState<Boolean> = mutableStateOf(false)
    val myCustomUI: myCustomUI = myCustomUI(context)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun profileView(){
        val imageSize = 120;
        var SP : SharedPreferences = context.getSharedPreferences("mcr",Context.MODE_PRIVATE)
        ConstraintLayout(modifier =Modifier.background(colorPalettes().darkBlues)) {
            val (image,content) = createRefs()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(50, 0, 0, 0)
                    )
                    .background(colorPalettes().darkBlues)
                    .constrainAs(content) {
                        top.linkTo(
                            image.bottom,
                            margin = -(imageSize / 2).dp
                        );start.linkTo(parent.start);end.linkTo(parent.end)
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(top = ((imageSize / 2) + 10).dp, start = 20.dp, end = 20.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(textAlign = TextAlign.Center, text = SP.getString("username","")!!, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text(textAlign = TextAlign.Center, text = SP.getString("email","")!!, color = Color.Black, fontSize = 15.sp)
                    myCustomUI.myButton(Icons.Default.Person, "Person",Modifier.padding(top=50.dp, bottom = 10.dp, start = 10.dp, end= 10.dp))
                    myCustomUI.myButton(Icons.Default.Settings, "Pengaturan & Privasi", Modifier.padding(10.dp))
                    myCustomUI.myButton(Icons.Default.Info, "About", Modifier.padding(10.dp))
                    myCustomUI.myButton(Icons.Default.ExitToApp, "Logout", Modifier.padding(10.dp),{
                        Toast.makeText(context,"Logout",Toast.LENGTH_SHORT).show()
                    })
                }
            }
            

            Box( modifier = Modifier
                .width(imageSize.dp)
                .height(imageSize.dp)
                .constrainAs(image) {
                    top.linkTo(
                        parent.top,
                        margin = 150.dp
                    );start.linkTo(parent.start);end.linkTo(parent.end)
                }
            ){
                AnimatedVisibility(
                    visible = showPicture.value,
                    enter = fadeIn(initialAlpha = 0.1f),
                ) {
                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .width(imageSize.dp)
                            .height(imageSize.dp)
                        ,
                        painter = painterResource(id = R.drawable.logo_umrah),
                        contentDescription = "Profile Picture")
                }
            }
        }
    }

    fun toggleProfile(time:Long, state:Boolean){
       android.os.Handler(Looper.getMainLooper()).postDelayed(Runnable {
               showPicture.value = state
       }, time)
    }
}
