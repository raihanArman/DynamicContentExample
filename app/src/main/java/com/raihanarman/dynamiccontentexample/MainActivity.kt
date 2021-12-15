package com.raihanarman.dynamiccontentexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
fun MainScreen(viewModel: MainViewModel = MainViewModel()){

    val newNameStateContent = viewModel.textFieldState.observeAsState("")

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        GreatingMessage(newNameStateContent.value){
                newValue ->   viewModel.onTextChanged(newValue)
        }
    }
}

@Composable
fun GreatingMessage(
        textFieldvalue: String,
        textFieldUpdate: (newValue: String) -> Unit
){

    TextField(value = textFieldvalue, onValueChange = textFieldUpdate)

    Button(onClick = {}){
        Text(textFieldvalue)
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