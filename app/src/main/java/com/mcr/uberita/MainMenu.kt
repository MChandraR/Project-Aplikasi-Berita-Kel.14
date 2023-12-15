package com.mcr.uberita

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mcr.uberita.fragment.bookmarkFragment
import com.mcr.uberita.fragment.homeFragment
import com.mcr.uberita.fragment.profileFragment
import com.mcr.uberita.ui.theme.UBeritaTheme
import com.mcr.uberita.util.colorPalettes
import com.mcr.uberita.util.menus

class MainMenu : ComponentActivity() {
    val context:Context = this
    var showBar:MutableState<Boolean> = mutableStateOf(true)
    val items = listOf(menus.Home, menus.Notif,menus.Bookmark, menus.Profile)
    //UI Class
    lateinit var homeFragments:homeFragment
    lateinit var profileFragments:profileFragment
    lateinit var bookmarkFragment:bookmarkFragment
    //UI Composalbe view
    lateinit var homes: @Composable () -> Unit
    lateinit var profiles:@Composable () -> Unit
    lateinit var bookmarks:@Composable () -> Unit

    var selectedScreen: MutableState<String> =  mutableStateOf(menus.Home.route)

    override fun onCreate(savedInstanceState: Bundle?) {
        homeFragments  = homeFragment(context)
        homes = { homeFragments.HomeView(this) }
        homeFragments.startChek()
        profileFragments  = profileFragment(this)
        profiles = {profileFragments.profileView()}
        bookmarkFragment = bookmarkFragment(this)
        bookmarks = {bookmarkFragment.bookmarkView()}

        super.onCreate(savedInstanceState)
        setContent {
            UBeritaTheme(color = colorPalettes().darkBlues) {
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
                    profileFragments.toggleProfile(0, false)
                }
                "profile" -> {
                    profiles()
                    profileFragments.toggleProfile(200, true)
                }

                "bookmark" ->{
                    bookmarks()
                    profileFragments.toggleProfile(0, false)
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
        AnimatedVisibility(
            visible = !homeFragments.scrollStat.value.isScrollInProgress,
            enter = slideInVertically(initialOffsetY = {100}),
            exit = slideOutVertically(targetOffsetY = {100})
        ) {
            MyNavigationBar(containerColor = colorPalettes().blue, modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(50, 50, 50, 50)
                )
                .background(colorPalettes().blue)) {
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
                            Text(text = replyDestination.title!!, color = Color.White)
                        }
                    )

                }
            }
        }


    }

    override fun onBackPressed() {
        if(homeFragments.showDetail.value || homeFragments.showSearch.value){
            homeFragments.showDetail.value = false
            homeFragments.showSearch.value = false
            homeFragments.searchFragment.enabled.value = true
        }else{
            super.onBackPressed()
        }
    }
}




@Composable
fun MyNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = NavigationBarDefaults.containerColor,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .height(70.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            content = content
        )
    }
}