package com.mcr.uberita.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class menus(var route:String, val title:String?, val icon: ImageVector){
    object  Home : menus("home","Home", Icons.Default.Home);
    object  Scan : menus("scan","Scan", Icons.Default.AccountBox);
    object  Profile : menus("profile","Profile", Icons.Default.Person);
    object  Rak : menus("rak","Rak",Icons.Default.ShoppingCart);
    object  Notif : menus("notification","Notification",Icons.Default.Notifications);
    object  Bookmark : menus("bookmark","Bookmark",Icons.Default.FavoriteBorder)
    object Setting : menus("settings","Settings",Icons.Default.Settings);
    object  Empty : menus("empty", "Empty",Icons.Default.Home);
}
