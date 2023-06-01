package com.example.roomdatabasenoteapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabasenoteapp.databinding.ActivityMainBinding
import com.example.roomdatabasenoteapp.room.NoteDao
import com.example.roomdatabasenoteapp.room.NoteDatabase
import com.example.roomdatabasenoteapp.room.NoteModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteList:ArrayList<NoteModel>
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteDao: NoteDao
    private lateinit var noteAdapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteDatabase= NoteDatabase.getNoteDatabase(this)
        noteDao=noteDatabase.getNoteDao()
        noteList=ArrayList<NoteModel>()
        noteAdapter= NoteAdapter(noteList)
        binding.recyclerView.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.adapter=noteAdapter

        binding.addNoteButton.setOnClickListener {
            val intent= Intent(this,AddNoteActivity::class.java)
            intent.putExtra("type","new")
            startActivity(intent)
        }

        delete()
    }

    override fun onResume() {
        super.onResume()
        getNote()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getNote(){
        noteList.clear()
        noteList.addAll(noteDao.getAllNote())
        noteAdapter.notifyDataSetChanged()
    }

    private fun delete(){
        noteAdapter.onItemClick={
            noteDao.deleteNote(it)
            getNote()
        }
    }
}