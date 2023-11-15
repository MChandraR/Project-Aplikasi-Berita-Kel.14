package com.mcr.uberita

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mcr.uberita.fragment.homeFragment
import com.mcr.uberita.ui.theme.UBeritaTheme
import com.mcr.uberita.util.colorPalette
import com.mcr.uberita.util.menus

class MainMenu : ComponentActivity() {
    val context:Context = this
    var showBar:MutableState<Boolean> = mutableStateOf(true)
    val items = listOf(menus.Home, menus.Notif,menus.Bookmark, menus.Profile)
    var selectedScreen: MutableState<String> =  mutableStateOf(menus.Home.route)
    val homes: @Composable () -> Unit = { homeFragment().HomeView() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UBeritaTheme(color = Color.White) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MainMenuView()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainMenuView(){
        Scaffold(
            bottomBar = {
                BotNavBar(context =this )
            },
            modifier = Modifier.background(Color.White)
        ) {
            val pad = it
            when(selectedScreen.value) {
                "home" -> {
                    homes()
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BotNavBar(context: Context){
        var refresh by remember {
            mutableStateOf(true)
        }
            NavigationBar(containerColor = colorPalette().blue, modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(50, 50, 50, 50)
                )
                .background(colorPalette().blue)) {
                items.forEach{ replyDestination ->
                    NavigationBarItem(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(all = 0.dp),
                        selected = selectedScreen.value == "",
                        onClick = {
                            selectedScreen.value = replyDestination.route
                        },
                        icon = {
                            Icon( tint =  if(selectedScreen.value==replyDestination.route) Color.Yellow else Color.White, imageVector =  replyDestination.icon, contentDescription = replyDestination.route )
                        },
                        label = {
                            Text(text = replyDestination.title!!)
                        }
                    )

                }
            }

    }
}


//    @Composable
//    fun bottomNavBar(){
//        AnimatedVisibility(
//            visible = showBar.value,
//            enter = scaleIn(initialScale = 0f)
//        ) {
//            BottomAppBar(
//                modifier = Modifier
//                    .height(75.dp)
//            ) {
//                BottomNavigation(
//                    backgroundColor = CP.Invisible,
//                    modifier = Modifier
//                        .padding(12.dp, 0.dp, 12.dp, 12.dp)
//                        .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
//                        .height(100.dp)
//                        .background(CP.largeRadialGradient),
//                    elevation = 0.dp
//                ) {
//                    items.forEach {
//                        var isIcon = false
//                        BottomNavigationItem(
//                            icon = {
//                                it.icon?.let {
//                                    isIcon = true
//                                    Icon(
//                                        imageVector = it,
//                                        contentDescription = "",
//                                        modifier = Modifier.size(30.dp),
//                                        tint = Color.White
//
//                                    )
//                                }
//                            },
//                            label = {
//                                it.title?.let {
//                                    var text = ""
//                                    if(isIcon) text = it
//                                    Text(
//                                        text = text,
//                                        color = Color.White,
//                                        fontSize = 11.sp
//                                    )
//                                }
//                            },
//                            selected = it.title == selectedScreen.value,
//                            onClick = {
//                                if(isIcon)selectedScreen.value = it.route
//                            }
//                        )
//                    }
//                }
//            }
//        }
//
//    }
//}
//
//
//@Composable
//fun FAB() {
//    AnimatedVisibility(
//        visible = showBar.value,
//        enter = scaleIn(initialScale = 0f)
//    ) {
//        FloatingActionButton(
//            modifier = Modifier.size(60.dp),
//            onClick = {
//                selectedScreen.value = "scan"
//            },
//            shape = CircleShape,
//            contentColor = Color.White,
//            containerColor = CP.LightBlue
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
//                contentDescription = "float_icon"
//            )
//
//        }
//    }
//}