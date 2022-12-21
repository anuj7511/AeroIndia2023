package universal.appfactory.aeroindia2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpeakersActivity : AppCompatActivity() {

    // variable for our adapter
    // class and array list
    private lateinit var adapter: SpeakersAdapter
    private lateinit var data: ArrayList<SpeakerModel>
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speakers)

        supportActionBar?.hide()

        // getting the recyclerview by its id
        recyclerview = findViewById(R.id.recycler_view)
        val refreshView = findViewById<SwipeRefreshLayout>(R.id.refreshLayout)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        data = ArrayList()
        fetchSpeakerData()

        refreshView.setOnRefreshListener{
            fetchSpeakerData()
            refreshView.isRefreshing = false
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun fetchSpeakerData () {
        val speakerApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            speakerApi.getSpeakers("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")?.enqueue(object :
                Callback<SpeakerResponse?> {
                override fun onResponse(
                    call: Call<SpeakerResponse?>,
                    response: Response<SpeakerResponse?>
                ) {

                    Log.d("Response: ", response.body().toString())
                    data = response.body()?.data as ArrayList<SpeakerModel>
                    // This will pass the ArrayList to our Adapter
                    adapter = SpeakersAdapter(data)
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