package com.example.hidenseek

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hidenseek.ui.theme.HideNSeekTheme
import com.google.firebase.database.*



/*
    - Screenvorlage als Test (noch nicht getestet)
    - Idee ist es diese Datei als Activity zu starten, wenn der Continue-Knopf des Gruppenerstellungs-Screens getätigt wird
    - noch nicht funktionstauglich

 */
class firebase1 : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HideNSeekTheme {
                // A surface container using the
                // 'background' color from the theme
                Surface(
                    // on below line we are specifying
                    // modifier and color for our app
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    // on the below line we are specifying the theme as the scaffold.
                    Scaffold(
                        // in scaffold we are specifying the top bar.
                        topBar = {
                            // inside top bar we are specifying background color.
                            TopAppBar(backgroundColor = Color.Green,
                                // along with that we are specifying
                                // title for our top bar.
                                title = {
                                    // in the top bar we are
                                    // specifying tile as a text
                                    Text(
                                        // on below line we are specifying
                                        // text to display in top app bar
                                        text = "GFG",
                                        // on below line we are specifying
                                        // modifier to fill max width
                                        modifier = Modifier.fillMaxWidth(),
                                        // on below line we are
                                        // specifying text alignment
                                        textAlign = TextAlign.Center,
                                        // on below line we are specifying
                                        // color for our text.
                                        color = Color.White
                                    )
                                })
                        }) {

                        // on below line creating variable for freebase database
                        // and database reference.
                        val firebaseDatabase = FirebaseDatabase.getInstance();
                        val databaseReference = firebaseDatabase.getReference("EmployeeInfo");

                        // on below line we are calling method to display UI
                        firebaseUI(LocalContext.current, databaseReference)
                    }
                }
            }
        }
    }
}

@Composable
fun firebaseUI(context: Context, databaseReference: DatabaseReference) {

    val name = remember {
        mutableStateOf(TextFieldValue())
    }

    val address = remember {
        mutableStateOf(TextFieldValue())
    }

    val contactNumber = remember {
        mutableStateOf(TextFieldValue())
    }

    // on below line creating a column to display our retrieved list.
    Column(
        // adding modifier for our column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White),
        // on below line adding vertical and horizontal alignment for column.
        verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Add data to Firebase Realtime Database",
            // in modifier we are specifying padding
            // for our text from all sides.
            modifier = Modifier.padding(10.dp),
            // on below line we are specifying
            // style for our text
            style = TextStyle(
                color = Color.Black, fontSize = 20.sp
            ), fontWeight = FontWeight.Bold
        )

        // on below line we are creating
        // a text field for our email.
        TextField(
            // on below line we are specifying
            // value for our email text field.
            value = name.value,

            // on below line we are adding on
            // value change for text field.
            onValueChange = { name.value = it },

            // on below line we are adding place holder
            // as text as "Enter your email"
            placeholder = { Text(text = "Enter your Name") },

            // on below line we are adding modifier to it
            // and adding padding to it and filling max width
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            // on below line we are adding text style
            // specifying color and font size to it.
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

            // on below line we are adding
            // single line to it.
            singleLine = true,
        )

        // on below line we are adding spacer
        Spacer(modifier = Modifier.height(10.dp))

        // on below line we are creating
        // a text field for our email.
        TextField(
            // on below line we are specifying
            // value for our email text field.
            value = contactNumber.value,

            // on below line we are adding on
            // value change for text field.
            onValueChange = { contactNumber.value = it },

            // on below line we are adding place holder
            // as text as "Enter your email"
            placeholder = { Text(text = "Enter your contact number") },

            // on below line we are adding modifier to it
            // and adding padding to it and filling max width
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            // on below line we are adding text style
            // specifying color and font size to it.
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

            // on below line we are adding
            // single line to it.
            singleLine = true,
        )

        // on below line we are adding spacer
        Spacer(modifier = Modifier.height(10.dp))

        // on below line we are creating
        // a text field for our email.
        TextField(
            // on below line we are specifying
            // value for our email text field.
            value = address.value,

            // on below line we are adding on
            // value change for text field.
            onValueChange = { address.value = it },

            // on below line we are adding place holder
            // as text as "Enter your email"
            placeholder = { Text(text = "Enter your address") },

            // on below line we are adding modifier to it
            // and adding padding to it and filling max width
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            // on below line we are adding text style
            // specifying color and font size to it.
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

            // on below line we are adding
            // single line to it.
            singleLine = true,
        )

        // on below line we are adding spacer
        Spacer(modifier = Modifier.height(10.dp))

        // on below line creating button
        Button(
            onClick = {
                // on below line we are adding data.
                val empObj =
                    userObj(name.value.text, contactNumber.value.text, address.value.text);
                // we are use add value event listener method
                // which is called with database reference.
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        // inside the method of on Data change we are setting
                        // our object class to our database reference.
                        // data base reference will sends data to firebase.
                        databaseReference.setValue(empObj)

                        // after adding this data we
                        // are showing toast message.
                        Toast.makeText(
                            context,
                            "Data added to Firebase Database",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // if the data is not added or it is cancelled then
                        // we are displaying a failure toast message.
                        Toast.makeText(
                            context,
                            "Fail to add data $error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            },
            // on below line we are
            // adding modifier to our button.
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // on below line we are adding text for our button
            Text(text = "Add Employee Details", modifier = Modifier.padding(8.dp))
        }
    }
}
