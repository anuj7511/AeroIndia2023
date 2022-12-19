package universal.appfactory.aeroindia2023


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class ExhibitorsActivity : AppCompatActivity() {

    // variable for our adapter
    // class and array list
    private lateinit var adapter: ExhibitorAdapter
    private lateinit var data: ArrayList<ExhibitorModel>
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exhibitors)

        // getting the recyclerview by its id
        recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        // getting searchview by its id
        val searchView = findViewById<SearchView>(R.id.search_bar)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        data = ArrayList()
        fetchExhibitorData()


    }


    @OptIn(DelicateCoroutinesApi::class)
    fun fetchExhibitorData () {
        val exhibitorApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            exhibitorApi.getExhibitors("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")?.enqueue(object :
                Callback<ExhibitorResponse?> {
                override fun onResponse(
                    call: Call<ExhibitorResponse?>,
                    response: Response<ExhibitorResponse?>
                ) {

                    Log.d("Response: ", response.body().toString())
                    data = response.body()?.data as ArrayList<ExhibitorModel>
                    // This will pass the ArrayList to our Adapter
                    adapter = ExhibitorAdapter(data)
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter

                }

                override fun onFailure(call: Call<ExhibitorResponse?>, t: Throwable) {
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