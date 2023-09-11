package com.example.fantasticgoals

public class Goal {
    private var title: String? = null
    private var description: String? = null

    fun Goal(title: String?, description: String?) {
        this.title = title
        this.description = description
    }

    fun getTitle(): String? {
        return title
    }

    fun getDescription(): String? {
        return description
    }

}