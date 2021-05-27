package com.example.composetodo

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.core.view.WindowCompat
import com.example.composetodo.ui.theme.ComposeTodoTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            App(todoViewModel.todos)
        }
    }
}

@Preview @Composable fun DefaultPreview() =
    App(listOf(
        Todo("Do stuff"),
        Todo("Write todo list app"),
        Todo("Contemplate the true meaning of \"todo\"")
    ))

@Composable
fun App(todos: List<Todo>) {
    ComposeTodoTheme {
        ProvideWindowInsets(consumeWindowInsets = false) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text("Todo List")
                        },
                    )
                },
                modifier = Modifier.systemBarsPadding()
            ) { innerPadding ->
                BodyContent(
                    todos,
                    Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun BodyContent(todos: List<Todo>, modifier: Modifier) {
    Column(modifier = modifier) {
        TodoList(todos, Modifier.weight(1f))
        TodoInput()
    }
}

@Composable
fun TodoList(todos: List<Todo>, modifier: Modifier = Modifier) {
    LazyColumn(modifier.fillMaxWidth()) {
        items(todos) { todo ->
            TodoItem(todo)
        }
    }
}

@Composable
fun TodoItem(todo: Todo) {
    val iconTint = if (false) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    }

    Column(Modifier.padding(4.dp)) {
        Row(Modifier.padding(vertical = 8.dp, horizontal = 2.dp)) {
            Icon(
                imageVector = Icons.Default.Event,
                contentDescription = "Icon",
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(todo.text)
        }
        Divider(
            modifier = Modifier.padding(),
            color = MaterialTheme.colors.primaryVariant,
            thickness = 1.dp
        )
    }
}

@Composable
fun TodoInput() {
    val (text, setText) = remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.imePadding()
    ) {
        TodoTextInput(
            text = text,
            onTextChange = setText,
            modifier = Modifier.weight(1f)
        )
        TodoSubmitButton()
    }
}

@Composable
//@OptIn(ExperimentalComposeUiApi::class)
fun TodoTextInput(text: String, onTextChange: (String) -> Unit, modifier: Modifier = Modifier) {
//    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Send,
            keyboardType = KeyboardType.Text,
        ),
        keyboardActions = KeyboardActions(onDone = {
//            keyboardController?.hide()
        }),
        //modifier = modifier
    )
}

@Composable
fun TodoSubmitButton(modifier: Modifier = Modifier) {
    Button(onClick = { /*TODO*/ }) {
        Text("Add")
    }
}
