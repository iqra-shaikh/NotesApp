package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNote.observe(this, Observer {list ->
            list?.let {
            adapter.updateList(it)
            }
        })
    }
    fun onItemClicked(note: Note){
        viewModel.deleteNote(note)
       Toast.makeText(this,"Note Deleted", Toast.LENGTH_SHORT).show()
   }

    fun submitData(view: View) {
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()) {
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"Note Inserted", Toast.LENGTH_SHORT).show()

        }
    }
}
