package com.example.hidenseek.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hidenseek.BottomBarScreen
import com.example.hidenseek.BottomNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)

    }
}


//MyScreen()
/*
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun MyScreen() {

    val navController = rememberNavController()
    val background: Painter = painterResource(R.drawable.background_2)
    val logo: Painter = painterResource(R.drawable.logo_white)

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
        Spacer(modifier = Modifier.height(8.dp))

    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = background,
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {

                Image(
                    painter = logo,
                    contentDescription = "Background image",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 16.dp)
                )

                Text(
                    text = "New Game",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    ),
                    modifier = Modifier.padding(bottom = 80.dp)
                )
            }

            //Spacer(modifier = Modifier.height(100.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {

                //Text(text = "ADD SEEKER", modifier = Modifier.padding(bottom = 8.dp))

                TextInputField(
                    label = "Add Seeker",
                    value = "", // Provide the initial value here
                    onValueChange = { newValue ->
                        //println("Hallo")
                        // Perform the desired action with the new value
                        // newValue is the updated text value
                        // Add your logic here
                    }
                )

                Button(
                    onClick = { /* Button 1 action */ },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(text = "Add more Seekers")
                }

                Spacer(modifier = Modifier.height(10.dp))

                TextInputField(
                    label = "Add Hider",
                    value = "", // Provide the initial value here
                    onValueChange = { newValue ->
                        //println("Hallo")
                        // Perform the desired action with the new value
                        // newValue is the updated text value
                        // Add your logic here
                    }
                )

                Button(
                    onClick = { /* Button 2 action */ },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(text = "Add more Hiders")
                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { /* Button 3 action */ },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(text = "Continue")
                }

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }

}


@Composable
fun TextInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = label)
        Spacer(modifier = Modifier.height(8.dp))
        val textState = remember { mutableStateOf(TextFieldValue(value)) }
        TextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
                onValueChange(it.text)
            },
            label = { Text(text = label) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
 */


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Games,
        BottomBarScreen.Settings
    )

    //observing navBackStackEntry + notifies when a change appears
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
//Extension Function on a rowscope
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                launchSingleTop = true
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true

            }
        }
    )
}


