package com.example.quicknote.ui.notesScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quicknote.R
import com.example.quicknote.models.Note

class NoteAdapter(private val items: List<Note>, val fn: (Note)->Unit) : RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = items[position]
        val noteId = currentNote.id
        val noteTitle = currentNote.title
        val noteDescription = currentNote.description
        holder.txtTitle.text = noteTitle
        holder.txtDec.text = noteDescription
        holder.itemView.setOnClickListener {
            fn(Note(noteId,noteTitle,noteDescription))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}


class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val txtTitle: TextView =itemView.findViewById(R.id.title)
    val txtDec: TextView = itemView.findViewById(R.id.desc)
}