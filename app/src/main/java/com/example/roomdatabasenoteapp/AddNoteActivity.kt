package com.example.roomdatabasenoteapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdatabasenoteapp.databinding.ActivityAddNoteBinding
import com.example.roomdatabasenoteapp.room.NoteDao
import com.example.roomdatabasenoteapp.room.NoteDatabase
import com.example.roomdatabasenoteapp.room.NoteModel

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteDao: NoteDao
    var id:Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteDatabase= NoteDatabase.getNoteDatabase(this)
        noteDao=noteDatabase.getNoteDao()
        noteSave()

        val type=intent.getStringExtra("type")
        if (type.equals("update")){
            val title=intent.getStringExtra("title")
            val detail=intent.getStringExtra("detail")
            id=intent.getIntExtra("id",-1)

            binding.addActivityTitle.setText(title)
            binding.addActivityDetail.setText(detail)
        }
    }
    @SuppressLint("SuspiciousIndentation")
    private fun noteSave(){
        binding.saveButton.setOnClickListener {
        val title=binding.addActivityTitle.text.toString()
        val detail=binding.addActivityDetail.text.toString()
            if (title.isNotEmpty() && detail.isNotEmpty()){
                val type=intent.getStringExtra("type")
                if (type.equals("new")){
                    noteDao.insertNote(NoteModel(
                        0,title,detail
                    ))
                }else{
                    noteDao.updateNote(NoteModel(
                        id,title,detail
                    ))
                }

                finish()
            }

        }
    }
}