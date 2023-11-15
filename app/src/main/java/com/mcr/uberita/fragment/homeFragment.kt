package com.mcr.uberita.fragment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mcr.uberita.R
import com.mcr.uberita.util.colorPalette

class homeFragment {
    @Composable
    fun HomeView(){
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(.1f).background(colorPalette().blue), verticalArrangement = Arrangement.Center) {
                Row(modifier = Modifier
                    .fillMaxWidth(),horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(.15f)) {
                        Image(modifier = Modifier.fillMaxWidth(),painter = painterResource(id = R.drawable.baseline_person_outline_24), contentDescription = "User image")
                    }
                    Column(modifier = Modifier.weight(.7f)) {
                        Text(modifier = Modifier.fillMaxWidth(), text = "DAILY NEWS", textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(.15f)) {
                        Image(modifier = Modifier.fillMaxWidth(),painter = painterResource(id = R.drawable.baseline_person_outline_24), contentDescription = "User image")
                    }
                }
            }
            Column(modifier = Modifier.weight(.9f).padding(all=10.dp)) {
                Text(text = "Hallo ges")
            }
        }

    }
}