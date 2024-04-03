package com.example.sharedviewmodel_kotlin_jetpackcomponent.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sharedviewmodel_kotlin_jetpackcomponent.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {

    private val _selectedItemId = MutableStateFlow<Int?>(null)
    val selectedItemId: StateFlow<Int?> = _selectedItemId

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    fun updateData(itemList: List<Item>, selectedItemId: Int?) {
        _items.value = itemList
        _selectedItemId.value = selectedItemId
    }
}
