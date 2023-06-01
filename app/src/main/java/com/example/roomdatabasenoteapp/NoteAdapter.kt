package com.example.roomdatabasenoteapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasenoteapp.databinding.RecyclerrowBinding
import com.example.roomdatabasenoteapp.room.NoteModel

class NoteAdapter(private val noteList:ArrayList<NoteModel>):RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    var onItemClick:((NoteModel)->Unit)?=null

    class NoteHolder(val recyclerrowBinding: RecyclerrowBinding):RecyclerView.ViewHolder(recyclerrowBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(RecyclerrowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note=noteList[position]
        holder.recyclerrowBinding.recyclerRowTitle.text=note.title
        holder.recyclerrowBinding.recyclerRowDetail.text=note.detail

        holder.recyclerrowBinding.imageViewDelete.setOnClickListener {
            onItemClick?.invoke(note)
        }

        holder.itemView.setOnClickListener {
            val intent=Intent(holder.itemView.context,AddNoteActivity::class.java)
            intent.putExtra("type","update")
            intent.putExtra("id",note.id)
            intent.putExtra("title",note.title)
            intent.putExtra("detail",note.detail)
            holder.itemView.context.startActivity(intent)
        }
    }
}