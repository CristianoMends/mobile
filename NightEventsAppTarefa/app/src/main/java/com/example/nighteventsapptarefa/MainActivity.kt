package com.example.nighteventsapptarefa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nighteventsapptarefa.ui.components.BottomNavigationBar
import com.example.nighteventsapptarefa.ui.components.DrawerContent
import com.example.nighteventsapptarefa.ui.components.TopBar
import com.example.nighteventsapptarefa.ui.screens.EventDetailsScreen
import com.example.nighteventsapptarefa.ui.screens.FavoritesScreen
import com.example.nighteventsapptarefa.ui.screens.HomeScreen
import com.example.nighteventsapptarefa.ui.screens.SubscribedEventsScreen
import com.example.nighteventsapptarefa.ui.theme.NightEventsAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val isDarkTheme = remember { mutableStateOf(false) }
            NightEventsAppTheme(darkTheme = isDarkTheme.value) {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = true,
                    drawerContent = {
                        DrawerContent(navController) {
                        }
                    },
                    content = {

                        Scaffold(
                            topBar = {
                                TopBar(
                                    onThemeToggle = { isDarkTheme.value = !isDarkTheme.value },
                                    onOpenDrawer = { scope.launch { drawerState.open() } }
                                )
                            },

                            bottomBar = { BottomNavigationBar(navController) }

                        ) { innerPadding ->
                            NavHost(
                                navController = navController,
                                startDestination = "home",
                                modifier = Modifier.padding(innerPadding)

                            ) {
                                composable("home") {
                                    HomeScreen(
                                        navController,
                                        context = LocalContext.current
                                    )
                                }
                                composable("events") { SubscribedEventsScreen(navController) }
                                composable("favorites") { FavoritesScreen(navController) }
                                composable("eventDetails/{eventId}") { backStackEntry ->
                                    val eventId = backStackEntry.arguments?.getString("eventId")
                                    EventDetailsScreen(eventId = eventId)
                                }
                            }
                        }
                    }
                )
            }

        }
    }
}