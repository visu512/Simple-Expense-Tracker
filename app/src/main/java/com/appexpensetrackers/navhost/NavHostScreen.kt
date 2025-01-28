package com.codewithfk.expensetracker.android

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.appexensetrackers.R
import com.appexpensetrackers.AddExpense
import com.appexpensetrackers.HomeScreen
import com.appexpensetrackers.ProfileScreen
import com.appexpensetrackers.statics.StatsScreen
import com.appexpensetrackers.ui.theme.zinc

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()
    var bottomBarVisibility by remember { mutableStateOf(true) }

    Scaffold(bottomBar = {
        AnimatedVisibility(visible = bottomBarVisibility) {
            NavigationBottomBar(
                navController = navController,
                items = listOf(
                    NavItem(route = "/home", icon = R.drawable.home),
                    NavItem(route = "/add", icon = R.drawable.baseline_add),
                    NavItem(route = "/stats", icon = R.drawable.chart),
                    NavItem(route = "/profile", icon = R.drawable.person),
                    // You can add more NavItems here if necessary
                )
            )
        }
    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "/home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = "/home") {
                bottomBarVisibility = true
                HomeScreen(navController)
            }
            composable(route = "/add") {
                bottomBarVisibility = false
                AddExpense(navController)
            }                            
            composable(route = "/add") {
                bottomBarVisibility = false
                AddExpense(navController)
            }
            composable(route = "/stats") {
                bottomBarVisibility = true
                StatsScreen(navController)
            }
            composable(route = "/profile") {
                bottomBarVisibility = true
               ProfileScreen(navController)
            }
        }
    }
}

data class NavItem(
    val route: String,
    val icon: Int
)

@Composable
fun NavigationBottomBar(
    navController: NavController,
    items: List<NavItem>
) {
    // Get the current route from the NavController
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    BottomAppBar(
        modifier = Modifier.height(56.dp) // Set the height of the BottomAppBar
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    // Clear the back stack and navigate to the item route.
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = null)
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = zinc,
                    selectedIconColor = zinc,
                    unselectedTextColor = Color.Gray,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}

