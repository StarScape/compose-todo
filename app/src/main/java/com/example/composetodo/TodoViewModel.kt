package com.example.composetodo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.*

data class Todo(
    val text: String,
    val uuid: UUID = UUID.randomUUID()
)

class TodoViewModel: ViewModel() {
    var todos by mutableStateOf(listOf(
        Todo("Do stuff"),
        Todo("Write todo list app"),
        Todo("Contemplate the true meaning of \"todo\"")
    ))
        private set

    fun addTodo(todo: Todo) {
        todos = todos + todo
    }

    fun removeTodo(todo: Todo) {
        todos = todos.toMutableList().also { it.remove(todo) }
    }
}