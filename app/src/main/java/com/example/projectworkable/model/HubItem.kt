package com.example.projectworkable.model

sealed class HubItem {

    data class Resource(
        val title: String,
        val description: String,
        val imageResId: Int,
        val tag: String,
        val linkUrl: String
    ) : HubItem()

    data class Tool(
        val title: String,
        val description: String,
        val iconResId: Int,
        val category: String,
        val packageName: String
    ) : HubItem()
}
