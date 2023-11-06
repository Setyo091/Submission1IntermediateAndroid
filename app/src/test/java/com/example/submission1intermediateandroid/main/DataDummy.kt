package com.example.submission1intermediateandroid.main

import com.example.submission1intermediateandroid.response.ListStoryItem

object DataDummy {
    fun generateDummyStoryResponse(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 0..100) {
            val story = ListStoryItem(
                i.toString(),
                "photoUrl + $i",
                "name $i",
                "description $i",
                "id $i"
            )
            items.add(story)
        }
        return items
    }
}