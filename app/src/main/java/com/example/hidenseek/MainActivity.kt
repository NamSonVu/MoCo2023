package com.example.hidenseek

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hidenseek.ui.theme.HideNSeekTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.hidenseek.screens.BottomBar
import androidx.compose.foundation.lazy.LazyColumn




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HideNSeekTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyScreen()
                    //HomeScreen()
                }

                
            }
        }
    }
}


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
            contentScale = FillBounds
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = logo,
                    contentDescription = "Logo image",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 16.dp)
                )
            }

            item {
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

            item {
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
            }

            item {
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
            }

            item {
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
