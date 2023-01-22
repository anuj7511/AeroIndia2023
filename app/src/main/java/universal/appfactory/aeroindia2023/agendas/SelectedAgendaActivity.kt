package universal.appfactory.aeroindia2023.agendas

import android.R.attr.*
import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.notes.AddNoteActivity
import universal.appfactory.aeroindia2023.ApiClient
import universal.appfactory.aeroindia2023.ApiInterface
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.notes.Note
import universal.appfactory.aeroindia2023.notes.NoteDatabase
import universal.appfactory.aeroindia2023.notes.NotesRVAdapter
import universal.appfactory.aeroindia2023.notes.RecyclerClickListener
import universal.appfactory.aeroindia2023.speakers.SpeakerModel
import universal.appfactory.aeroindia2023.speakers.SpeakerResponse
import universal.appfactory.aeroindia2023.speakers.SpeakersAdapter
import java.util.*


class SelectedAgendaActivity : AppCompatActivity() {

    private lateinit var speakersAdapter: SpeakersAdapter
    private lateinit var data: ArrayList<SpeakerModel>
    private lateinit var recyclerview: RecyclerView
    private lateinit var notesRecyclerview: RecyclerView
    private lateinit var adapter: NotesRVAdapter
    private val noteDatabase by lazy { NoteDatabase.getDatabase(this).noteDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_agenda)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        val set = ConstraintSet()
        val parent = findViewById<ConstraintLayout>(R.id.parent)
        val agenda = findViewById<TextView>(R.id.agenda)
        val time = findViewById<TextView>(R.id.time)
        val date = findViewById<TextView>(R.id.date)
        val information = findViewById<TextView>(R.id.information)
        val speakers = findViewById<TextView>(R.id.speakers)
        val notes = findViewById<TextView>(R.id.notes)
        val scrollView = findViewById<ScrollView>(R.id.scrollView)
        val addButton = findViewById<FloatingActionButton>(R.id.add_button)
        recyclerview = findViewById(R.id.recycler_view)
        notesRecyclerview = findViewById(R.id.notes_recyclerview)

        set.clone(parent)

        val locationText = findViewById<TextView>(R.id.location)
        val categoryText = findViewById<TextView>(R.id.category)
        val descriptionText = findViewById<TextView>(R.id.description)

        val id = intent.getIntExtra("Id", 1)
        val name = intent.getStringExtra("Name")
        val startTime = intent.getStringExtra("Start Time")
        val endTime = intent.getStringExtra("End Time")
        val dateText = intent.getStringExtra("Date")
        val location = intent.getStringExtra("Location")
        val category = intent.getStringExtra("Category")
        val description = intent.getStringExtra("Description")

        val timeVal = "$startTime-$endTime"

        agenda.text = name
        time.text = timeVal
        date.text = dateText
        locationText.text = location
        categoryText.text = category
        descriptionText.text = description
        information.paintFlags = information.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        recyclerview.layoutManager = LinearLayoutManager(this)
        data = ArrayList()
        fetchSpeakerData(id)
        setRecyclerView()
        observeNotes()


        speakers.setOnClickListener {
            speakers.paintFlags = speakers.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            information.paintFlags = 0
            notes.paintFlags = 0
            scrollView.visibility = View.INVISIBLE
            recyclerview.visibility = View.VISIBLE
            notesRecyclerview.visibility = View.INVISIBLE
            addButton.visibility = View.INVISIBLE
        }

        information.setOnClickListener {
            information.paintFlags = information.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            speakers.paintFlags = 0
            notes.paintFlags = 0
            scrollView.visibility = View.VISIBLE
            recyclerview.visibility = View.INVISIBLE
            notesRecyclerview.visibility = View.INVISIBLE
            addButton.visibility = View.INVISIBLE
        }

        notes.setOnClickListener {
            notes.paintFlags = notes.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            information.paintFlags = 0
            speakers.paintFlags = 0
            scrollView.visibility = View.INVISIBLE
            recyclerview.visibility = View.INVISIBLE
            notesRecyclerview.visibility = View.VISIBLE
            addButton.visibility = View.VISIBLE
        }

        addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            newNoteResultLauncher.launch(intent)
            observeNotes()
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchSpeakerData(id: Int) {
        val speakerApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO) {

            speakerApi.getAgendaSpeaker("Bearer 61b25a411a2dad66bb7b6ff145db3c2f", id)
                ?.enqueue(object :
                    Callback<SpeakerResponse?> {
                    override fun onResponse(
                        call: Call<SpeakerResponse?>,
                        response: Response<SpeakerResponse?>
                    ) {

                        Log.d("Response: ", response.body().toString())
                        data = response.body()?.data as ArrayList<SpeakerModel>
                        Collections.sort(data, SortByName())
                        // This will pass the ArrayList to our Adapter
                        speakersAdapter = SpeakersAdapter(data, this@SelectedAgendaActivity)
                        // Setting the Adapter with the recyclerview
                        recyclerview.adapter = speakersAdapter

                    }

                    override fun onFailure(call: Call<SpeakerResponse?>, t: Throwable) {
                        Toast.makeText(
                            applicationContext, t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("Failure Response: ", t.message.toString())
                    }
                })


        }
    }

    private fun setRecyclerView() {

        notesRecyclerview.layoutManager = LinearLayoutManager(this)
        notesRecyclerview.setHasFixedSize(true)
        adapter = NotesRVAdapter()
        adapter.setItemListener(object : RecyclerClickListener {

            // Tap the 'X' to delete the note.
            override fun onItemRemoveClick(position: Int) {
                val notesList = adapter.currentList.toMutableList()
                val noteText = notesList[position].noteText
                val noteDateAdded = notesList[position].dateAdded
                val removeNote = Note(noteDateAdded, noteText)
                lifecycleScope.launch {
                    noteDatabase.deleteNote(removeNote)
                }
            }

            // Tap the note to edit.
            override fun onItemClick(position: Int) {
                val intent = Intent(this@SelectedAgendaActivity, AddNoteActivity::class.java)
                val notesList = adapter.currentList.toMutableList()
                intent.putExtra("note_date_added", notesList[position].dateAdded)
                intent.putExtra("note_text", notesList[position].noteText)
                editNoteResultLauncher.launch(intent)
            }
        })
        notesRecyclerview.adapter = adapter
    }

    private fun observeNotes() {
        lifecycleScope.launch {
            noteDatabase.getNotes().collect { notesList ->
                if (notesList.isNotEmpty()) {
                    adapter.submitList(notesList)
                }
            }
        }
    }

    private val newNoteResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Get the new note from the AddNoteActivity
                val noteDateAdded = Date()
                val noteText = result.data?.getStringExtra("note_text")
                // Add the new note at the top of the list
                val newNote = Note(noteDateAdded, noteText ?: "")
                lifecycleScope.launch {
                    noteDatabase.addNote(newNote)
                }
                observeNotes()
            }
        }

    val editNoteResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Get the edited note from the AddNoteActivity
                val noteDateAdded = result.data?.getSerializableExtra("note_date_added") as Date
                val noteText = result.data?.getStringExtra("note_text")
                // Update the note in the list
                val editedNote = Note(noteDateAdded, noteText ?: "")
                lifecycleScope.launch {
                    noteDatabase.updateNote(editedNote)
                }
            }
        }

    private class SortByName : Comparator<SpeakerModel> {
        override fun compare(
            object1: SpeakerModel,
            object2: SpeakerModel
        ): Int {
            val name1: String = object1.getFirst_name().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getFirst_name().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }
}

