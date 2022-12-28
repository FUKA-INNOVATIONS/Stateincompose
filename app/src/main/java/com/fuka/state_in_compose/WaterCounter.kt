package com.fuka.state_in_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {

        /*
        * remember stores objects in the Composition,
        * and forgets the object if the source location where remember is called is not invoked again during a recomposition.
        * */

        /*
        * While remember helps you retain state across recompositions,
        * it's not retained across configuration changes. For this, you must use rememberSaveable instead of remember.
        *
        * rememberSaveable automatically saves any value that can be saved in a Bundle.
        * For other values, you can pass in a custom saver object.
        * */

        /*
        * Use rememberSaveable to restore your UI state after an Activity or process is recreated.
        * Besides retaining state across recompositions, rememberSaveable also retains state across Activity and process recreation.
        * */

        // Changes to count are now tracked by Compose
        // val count: MutableState<Int> = remember { mutableStateOf(0) }    // remember = Composable inline function (state.value)
        // var count by remember { mutableStateOf(0) }                   // by = Kotlin's delegated keyword

        var count by rememberSaveable { mutableStateOf(0) }

        if (count > 0) {
            // This text is present if the button has been clicked at least once; absent otherwise
            Text("You've had $count glasses.")
        }

        Button(onClick = { count++ }, enabled = count < 10) {
            Text("Add one")
        }
    }
}

/*
* Key Point: A best practice for the design of Composables is to pass them only the parameters they need.
* */

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count, { count++ }, modifier)
}