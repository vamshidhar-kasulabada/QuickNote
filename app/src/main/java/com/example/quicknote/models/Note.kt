package com.example.quicknote.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Note(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val description: String?
)
