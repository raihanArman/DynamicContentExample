package com.raihanarman.dynamiccontentexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raihanarman.dynamiccontentexample.ui.theme.DynamicContentExampleTheme

//val nameList:ArrayList<String> = arrayListOf("john", "Michael", "Andrea", "Danna")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(){
    val greetingListState = remember {
        mutableStateListOf<String>("John")
    }

    val newNameStateContent = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        GreatingList(greetingListState, {
            greetingListState.add(newNameStateContent.value)
        },newNameStateContent.value,
            {
              newValue ->   newNameStateContent.value
            })
    }
}

@Composable
fun GreatingList(
        nameList: List<String>,
        buttonClick: () -> Unit,
        textFieldvalue: String,
        textFieldUpdate: (newValue: String) -> Unit
){
    for (name in nameList){
        Greeting(name = name)
    }

    
    TextField(value = textFieldvalue, onValueChange = textFieldUpdate)

    Button(onClick = buttonClick){
        Text("Add new name")
    }
}

@Composable
fun Greeting(name: String) {
    Text("$name", style = MaterialTheme.typography.h3)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}