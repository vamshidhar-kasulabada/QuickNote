package com.example.quicknote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quicknote.models.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NotesDataBase: RoomDatabase() {


    abstract fun getNoteDao(): NotesDAO

}