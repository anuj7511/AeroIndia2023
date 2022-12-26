package universal.appfactory.aeroindia2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZonalManagerActivity : AppCompatActivity() {
    private lateinit var adapter: ZonalManagerAdapter
    private lateinit var data: ArrayList<ZonalManagerModel>
    private lateinit var recyclerview: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zonal_manager)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        recyclerview = findViewById<RecyclerView>(R.id.recycler_view)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        data = ArrayList()
        fetchPData()   }


    @OptIn(DelicateCoroutinesApi::class)
    fun fetchPData () {
        val ZApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            ZApi.getmanagers("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")?.enqueue(object :
                Callback<ZonalManagerResponse?> {
                override fun onResponse(
                    call: Call<ZonalManagerResponse?>,
                    response: Response<ZonalManagerResponse?>,
                ) {

                    Log.d("Response: ", response.body().toString())
                    data = response.body()?.data as ArrayList<ZonalManagerModel>
                    // This will pass the ArrayList to our Adapter
                    adapter = ZonalManagerAdapter(data)
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter

                }

                override fun onFailure(call: Call<ZonalManagerResponse?>, t: Throwable) {
                    Toast.makeText(
                        applicationContext, t.message,
                        Toast.LENGTH_SHORT,
                    ).show()
                    Log.d("Failure Response: ", t.message.toString())
                }
            },
            )


        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

}
