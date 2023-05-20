package com.example.hidenseek.screens

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hidenseek.R

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileScreen()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun ProfileScreen() {
    val backgroundImage = painterResource(id = R.drawable.background_2)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = backgroundImage,
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(1.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo_white),
                    contentDescription = "Logo Placeholder"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Profile", style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.height(20.dp))
                EditableTextField(label = "Name")
                Spacer(modifier = Modifier.height(20.dp))
                EditableTextField(label = "Email")
                Spacer(modifier = Modifier.height(20.dp))
                EditableTextField(label = "Password")
            }
        }
    }
}

@Composable
fun EditableTextField(label: String) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.primary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileScreen()
}