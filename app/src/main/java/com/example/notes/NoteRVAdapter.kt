package com.example.notes

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(private val context: Context, private val listener: INotesRVAdapter) :
    RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    val allNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val holder =
            NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        holder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotes[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateNoteList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val textView: TextView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton: ImageView = itemView.findViewById<ImageView>(R.id.deleteButton)

    }
}


interface INotesRVAdapter {
    fun onItemClicked(note: Note)
}