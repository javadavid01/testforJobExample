package com.example.test.Model

data class ItemListViewState(
    val toolbarTitle: String,
    val items: List<ItemRow>
)

data class ItemRow(val name: String)