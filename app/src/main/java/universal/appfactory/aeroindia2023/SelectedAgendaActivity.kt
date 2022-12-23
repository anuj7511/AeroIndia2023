package universal.appfactory.aeroindia2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedAgendaActivity : AppCompatActivity() {

    private lateinit var adapter: SpeakersAdapter
    private lateinit var data: ArrayList<SpeakerModel>
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_agenda)

        val set = ConstraintSet()
        val parent = findViewById<ConstraintLayout>(R.id.parent)
        val agenda = findViewById<TextView>(R.id.agenda)
        val time = findViewById<TextView>(R.id.time)
        val date = findViewById<TextView>(R.id.date)
        val information = findViewById<TextView>(R.id.information)
        val speakers = findViewById<TextView>(R.id.speakers)
        val notes = findViewById<TextView>(R.id.notes)
        val underLine = findViewById<ImageView>(R.id.under_line)
        val scrollView = findViewById<ScrollView>(R.id.scrollView)
        recyclerview = findViewById(R.id.recycler_view)

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

        recyclerview.layoutManager = LinearLayoutManager(this)
        data = ArrayList()
        fetchSpeakerData(id)


        speakers.setOnClickListener {
            set.connect(underLine.id, ConstraintSet.END, parent.id, ConstraintSet.END)
            set.connect(underLine.id, ConstraintSet.START, parent.id, ConstraintSet.START)
            set.applyTo(parent)
            scrollView.visibility = View.INVISIBLE
            recyclerview.visibility = View.VISIBLE
        }

        information.setOnClickListener {
            set.clear(underLine.id, ConstraintSet.END)
            set.connect(underLine.id, ConstraintSet.START, parent.id, ConstraintSet.START)
            set.applyTo(parent)
            scrollView.visibility = View.VISIBLE
            recyclerview.visibility = View.INVISIBLE
        }

        notes.setOnClickListener {
            set.clear(underLine.id, ConstraintSet.START)
            set.connect(underLine.id, ConstraintSet.END, parent.id, ConstraintSet.END)
            set.applyTo(parent)
            scrollView.visibility = View.INVISIBLE
            recyclerview.visibility = View.INVISIBLE
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchSpeakerData (id:Int) {
        val speakerApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            speakerApi.getAgendaSpeaker("Bearer 61b25a411a2dad66bb7b6ff145db3c2f", id)?.enqueue(object :
                Callback<SpeakerResponse?> {
                override fun onResponse(
                    call: Call<SpeakerResponse?>,
                    response: Response<SpeakerResponse?>
                ) {

                    Log.d("Response: ", response.body().toString())
                    data = response.body()?.data as ArrayList<SpeakerModel>
                    // This will pass the ArrayList to our Adapter
                    adapter = SpeakersAdapter(data, this@SelectedAgendaActivity)
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter

                }

                override fun onFailure(call: Call<SpeakerResponse?>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message,
                        Toast.LENGTH_SHORT).show()
                    Log.d("Failure Response: ", t.message.toString())
                }
            })


        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

}

