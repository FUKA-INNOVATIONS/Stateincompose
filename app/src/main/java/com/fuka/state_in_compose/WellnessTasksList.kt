package com.fuka.state_in_compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
The items method receives a key parameter.
By default, each item's state is keyed against the position of the item in the list.

In a mutable list, this causes issues when the data set changes, since items that change position effectively lose any remembered state.

You can easily fix this by using the id of each WellnessTaskItem as the key for each item.
*/


@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
                onClose = { onCloseTask(task) }
            )
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