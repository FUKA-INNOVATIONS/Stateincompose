package com.fuka.state_in_compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember


private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = remember { getWellnessTasks() }
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(list) { task ->
            WellnessTaskItem(taskName = task.label)
        }
    }
}



/*  Notice the implementation of LazyColumn:
* The composable function rememberLazyListState creates an initial state for the list using rememberSaveable.
* When the Activity is recreated, the scroll state is maintained without you having to code anything.

Many apps need to react and listen to scroll position, item layout changes,
* and other events related to the list's state. Lazy components,
* like LazyColumn or LazyRow, support this use case through hoisting the LazyListState.
* You can learn more about this pattern in the documentation for state in lists.
*
* Having a state parameter with a default value provided
* by a public rememberX function is a common pattern in built-in composable functions.
* Another example can be found in Scaffold, which hoists state using rememberScaffoldState.
* */