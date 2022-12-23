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
import java.util.*

class SelectedSpeakerActivity : AppCompatActivity() {

    private lateinit var adapter: AgendaAdapter
    private lateinit var data: ArrayList<AgendaModel>
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_speaker)

        val set = ConstraintSet()
        val parent = findViewById<ConstraintLayout>(R.id.parent)
        val information = findViewById<TextView>(R.id.information)
        val agenda = findViewById<TextView>(R.id.agenda)
        val underLine = findViewById<ImageView>(R.id.under_line)
        val scrollView = findViewById<ScrollView>(R.id.scrollView)
        recyclerview = findViewById(R.id.recycler_view)

        set.clone(parent)

        val nameText = findViewById<TextView>(R.id.name)
        val descriptionText = findViewById<TextView>(R.id.biography)
        val speakerImage = findViewById<ImageView>(R.id.speaker_img)

        val id = intent.getIntExtra("Id", 1)
        val name = intent.getStringExtra("Name")
        val image = intent.getStringExtra("Image")
        val description = intent.getStringExtra("Biography")

        nameText.text = name
        descriptionText.text = description

        Glide.with(this@SelectedSpeakerActivity).load(image).into(speakerImage)

        recyclerview.layoutManager = LinearLayoutManager(this)
        data = ArrayList()
        fetchAgendaData(id)

        agenda.setOnClickListener {
            set.clear(underLine.id, ConstraintSet.START)
            set.connect(underLine.id, ConstraintSet.END, parent.id, ConstraintSet.END)
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
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchAgendaData (id: Int) {
        val agendaApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            agendaApi.getSpeakerAgenda("Bearer 61b25a411a2dad66bb7b6ff145db3c2f", id)?.enqueue(object :
                Callback<AgendaResponse?> {
                override fun onResponse(
                    call: Call<AgendaResponse?>,
                    response: Response<AgendaResponse?>
                ) {

                    Log.d("Response: ", response.body().toString())
                    data = response.body()?.data as ArrayList<AgendaModel>
                    // This will pass the ArrayList to our Adapter
                    adapter = AgendaAdapter(data, this@SelectedSpeakerActivity)
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter

                }

                override fun onFailure(call: Call<AgendaResponse?>, t: Throwable) {
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