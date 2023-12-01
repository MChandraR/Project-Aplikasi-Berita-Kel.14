package com.mcr.uberita.fragment

import android.content.Context
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mcr.uberita.R
import com.mcr.uberita.util.colorPalette
import com.mcr.uberita.util.myCustomUI
import java.security.AccessController.getContext
import java.util.logging.Handler
import kotlin.math.abs


class homeFragment {
    val category = listOf<String>("Sport","Health","Tech","Local")
    val myCustomUI:myCustomUI = myCustomUI()
    var selectedCategory:MutableState<String> = mutableStateOf(category.get(0))
    var columnSize = mutableStateOf(IntSize.Zero)
    val dataBerita = listOf<String>("Berita pertama", "Berita kedua", "Berita ketiga", "Berita keempat","Berita kelima", "Berita keenam", "Berita ketujuh","Berita kedelapan")
    var context:Context? = null
    var scrollStat:MutableState<LazyListState> = mutableStateOf( LazyListState() )
    var isScrolling:MutableState<Boolean> = mutableStateOf(false)
    var scrollTemp = scrollStat

//    val runnable:Runnable = Runnable {
//        Toast.makeText(context, scrollStat.toString(), Toast.LENGTH_SHORT).show()
//        isScrolling.value = scrollStat.isScrollInProgress
//        startChek()
//    }

    fun startChek(){
//        android.os.Handler(Looper.getMainLooper()).postDelayed(
//            runnable
//            ,1000)
    }

    fun homeFragment(){
        startChek()
    }

    @Composable
    fun HomeView(context: Context){
        this.context = context
        val scale =  context.resources.displayMetrics.density

        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .weight(.075f)
                .background(colorPalette().blue), verticalArrangement = Arrangement.Center) {
                Row(modifier = Modifier
                    .fillMaxWidth(),horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(.15f)) {
                        Image(modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 5.dp),painter = painterResource(id = R.drawable.logo_umrah), contentDescription = "User image")
                    }
                    Column(modifier = Modifier.weight(.7f)) {
                        Text(modifier = Modifier.fillMaxWidth(), color=Color.White, text = "DAILY NEWS", textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(.15f)) {
                        Image(modifier = Modifier.fillMaxWidth(), colorFilter = ColorFilter.tint(Color.White), painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = "User image")
                    }
                }
            }
            Column(modifier = Modifier
                .weight(.925f)
                .background(MaterialTheme.colorScheme.background)
                .padding(all = 10.dp)
                .onGloballyPositioned {
                    columnSize.value = it.size
                }
            ) {
                val density = LocalDensity.current
                Row{
                    category.forEach {
                        Button(
                            colors = ButtonDefaults.buttonColors(if(it==selectedCategory.value) Color.Yellow else colorPalette().darkBlue),
                            modifier = Modifier.padding(horizontal = 5.dp),
                            onClick = {
                                Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
                                selectedCategory.value = it
                            }
                        ) {
                            Text(text = it, color = if(it!=selectedCategory.value) Color.White else colorPalette().darkBlue)
                        }
                    }
                }

                Row(
                    Modifier
                        .padding(top = 10.dp)
                        .horizontalScroll(rememberScrollState())) {
                    category.forEach {
                        Banner(columnSize.value)
                    }
                }
                Text(modifier = Modifier.padding(bottom=5.dp),text = "For You",fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.SansSerif, color = MaterialTheme.colorScheme.primary)
                LazyColumn(
                    state = scrollStat.value,
                    userScrollEnabled = true,
                    content = {
                        items(
                            count = dataBerita.size,
                            key = {
                                dataBerita[it]
                            },
                            itemContent = {
                                myCustomUI.beritaListView(judul = dataBerita[it])
                            }
                        )
                        item { 
                            Box(modifier = Modifier.height(80.dp))
                        }
                    }
                )

            }
        }

    }


    @Composable
    fun Banner(width: IntSize){
        val density = this.context!!.resources.displayMetrics.density
        Box{
            ConstraintLayout(modifier = Modifier
                .width((width.width / density - 0.5f).dp)
                .padding(horizontal = 5.dp)) {
                val (bg,content) = createRefs()
                Image(
                    painterResource(R.drawable.berita_banner),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(15, 15, 15, 15))
                        .constrainAs(bg) {
                            start.linkTo(parent.start)
                        }
                        .height(125.dp)
                        .graphicsLayer { alpha = 0.99f }
                        .drawWithContent {
                            val colors = listOf(
                                Color.Black,
                                Color.Transparent
                            )
                            drawContent()
                            drawRoundRect(
                                blendMode = BlendMode.DstOut,
                                brush = Brush.verticalGradient(colors = colors),
                            )
                        }
                )

                Column(
                    modifier = Modifier
                        .padding(top = 30.dp, start = 10.dp)
                        .constrainAs(content) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    Text(maxLines = 2, text = "Judul Berita", color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Bold)
                    Text(
                        style = TextStyle(shadow = Shadow(MaterialTheme.colorScheme.inverseSurface, blurRadius = .5f)),
                        maxLines = 4,
                        modifier = Modifier
                            .padding(top = 5.dp, end = 5.dp)
                            .height(100.dp),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 14.sp,
                        text = "Deskripsi berita , field ini berisi kontent atau deskripsi singkat dari berita yang aka ditamplkan" , color = MaterialTheme.colorScheme.outline,)
                }

            }
        }
    }
}
