package com.vladv1v.mangazanime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vladv1v.mangazanime.ui.library.LibraryListScreen
import com.vladv1v.mangazanime.ui.moresettings.SettingsScreen
import com.vladv1v.mangazanime.ui.theme.MangaZAnimeTheme
import com.vladv1v.mangazanime.ui.updates.UpdatesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MangaZAnimeTheme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier.wrapContentHeight()) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(painter = painterResource(id = screen.icon) , contentDescription = screen.title, modifier = Modifier.size(24.dp))},
                        label = { Text(text = screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }

                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ScreenL.LibraryList.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(ScreenL.LibraryList.route) { LibraryListScreen() }
            composable(ScreenL.UpdatesList.route) { UpdatesScreen() }
            composable(ScreenL.Settings.route) { SettingsScreen() }
        }
    }
}

sealed class ScreenL(var title: String, @DrawableRes val icon: Int, val route: String) {
    object LibraryList : ScreenL("Library", R.drawable.book, "library_list")
    object UpdatesList : ScreenL("Updates", R.drawable.updates, "updates")
    object Settings : ScreenL("Settings", R.drawable.settings, "settings")
}

val items = listOf(
    ScreenL.LibraryList,
    ScreenL.UpdatesList,
    ScreenL.Settings
)