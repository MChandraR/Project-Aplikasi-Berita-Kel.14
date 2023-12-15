package com.mcr.uberita.fragment

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mcr.uberita.util.myCustomUI

class searchFragment(var context: Context) {
    val myCustomUI:myCustomUI = myCustomUI(context)

    var keyword:MutableState<String> = mutableStateOf("")
    var enabled:MutableState<Boolean> = mutableStateOf(true)
    @Composable
    fun searchView(show:MutableState<Boolean>,context: Context){
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)) {
            myCustomUI.mySearchBar(
                modifiers = Modifier.padding(10.dp),
                actions = {

                },
                cancel = {
                    keyword.value = ""
                    show.value = false
                },
                keyword = keyword
            )
            
            Text(text = "Menampilkan hasil pencarian ...")
        }
    }
}