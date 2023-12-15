package com.mcr.uberita.fragment

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mcr.uberita.interfaces.bookMarkAPI
import com.mcr.uberita.model.beritaModel
import com.mcr.uberita.model.bookMarkModel
import com.mcr.uberita.util.clientAPI
import com.mcr.uberita.util.colorPalettes
import com.mcr.uberita.util.myCustomUI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class bookmarkFragment(val context: Context) {
    val myCustomUI:myCustomUI = myCustomUI(context)
    val cp:colorPalettes = colorPalettes()
    var bookmarkData:MutableList<beritaModel> = mutableListOf()
    var keyword:MutableState<String> = mutableStateOf("")
    var showDetail:MutableState<Boolean> = mutableStateOf(false)
    var targetBookmark : MutableState<beritaModel> = mutableStateOf(beritaModel())
    val SP:SharedPreferences = context.getSharedPreferences("mcr",Context.MODE_PRIVATE)

   init {
       getUserBookmark()
   }

    @Composable
    fun bookmarkView(){
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()) {
            val (search, container, detail) = createRefs()

            myCustomUI.mySearchBar(
                modifiers = Modifier
                    .padding(10.dp)
                    .constrainAs(search) {
                    top.linkTo(parent.top) ; bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start) ; end.linkTo(parent.end)
                },
                actions = {
                          showAction()
                },
                cancel = {
                    Toast.makeText(context, keyword.value , Toast.LENGTH_SHORT).show()
                },
                keyword = keyword
            )

            bookMarkListView(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .constrainAs(container) {
                        top.linkTo(parent.top, 50.dp);
                        start.linkTo(parent.start); end.linkTo(parent.end)
                    }
            )

            AnimatedVisibility(
                modifier  = Modifier.constrainAs(detail){
                    start.linkTo(parent.start); end.linkTo(parent.end)
                    top.linkTo(parent.top);bottom.linkTo(parent.bottom)
                },
                visible = showDetail.value,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                myCustomUI.beritaDetailView(onClickAction = {
                    showDetail.value  = false
                },dataBerita = targetBookmark)
            }
        }
    }

    fun getUserBookmark(){
        var clientAPI = clientAPI().getClientAPI()
        var bookmarkAPI = clientAPI.create(bookMarkAPI::class.java)
        var resData = bookmarkAPI.getUserBookmark(bookMarkModel(beritaModel(),SP.getString("user_id","")!!))

        resData.enqueue(object:Callback<bookMarkModel>{
            override fun onResponse(call: Call<bookMarkModel>, response: Response<bookMarkModel>) {
                Toast.makeText(context,response.body()!!.message,Toast.LENGTH_SHORT).show()
                bookmarkData.clear()
                bookmarkData.addAll(response.body()!!.data)
            }

            override fun onFailure(call: Call<bookMarkModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun showAction(){
        Toast.makeText(context,keyword.value,Toast.LENGTH_SHORT).show()
    }

    @Composable
    fun bookMarkListView(modifier: Modifier){
        Column (modifier = modifier){
            Text(modifier = Modifier.padding(vertical = 10.dp),text = "List bookmark anda " , fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline)
            LazyColumn(
                state = rememberLazyListState(),
                content = {
                    items(
                        count = bookmarkData.size,
                        key = {
                            bookmarkData[it].judul + it.toString()
                        },
                        itemContent = {
                            myCustomUI.beritaListView(data = bookmarkData[it]) {
                                showDetail.value = true
                                targetBookmark.value = bookmarkData[it]
                            }
                        }
                    )
                }
            )
        }
    }


}