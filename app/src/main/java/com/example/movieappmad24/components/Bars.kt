package com.example.movieappmad24.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieappmad24.navigation.getNavItems
import com.example.movieappmad24.navigation.Screens
import com.example.movieappmad24.ui.theme.Purple40


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenTopAppBar(title: String, details: Boolean, navController: NavController? = null) {
    return TopAppBar(
        title = { Text(text = title) },
        navigationIcon =
        {
            if (details) {
                IconButton(onClick = { navController!!.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple40),
    )
}

@Composable
fun GenBottomBar(navController: NavController, currentIndex: Int) {
    val navItems = getNavItems()
    return NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentIndex == index,
                onClick = {
                    if (index != currentIndex) navController.navigate(item.title.lowercase())
                },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    Icon(
                        imageVector = if (index == currentIndex) item.selectedIcon
                        else item.unselectedIcon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}