package universal.appfactory.aeroindia2023

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserHistoryActivity : AppCompatActivity() {
    private lateinit var adapter: UserHistoryAdapter
    private  var data: ArrayList<UserHistoryModel>? = null
    private lateinit var recyclerview: RecyclerView
     var user_id:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_history)
        recyclerview = findViewById<RecyclerView>(R.id.recycler_view)


        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)

        // ArrayList of class ItemsViewModel
        data = ArrayList()
        val intent = intent
         user_id = intent.getStringExtra("Name")!!.toInt()
        fetchPData()   }


    @OptIn(DelicateCoroutinesApi::class)
    fun fetchPData () {
        val UApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO) {

            UApi.gethistory("Bearer 61b25a411a2dad66bb7b6ff145db3c2f",user_id)?.enqueue(
                object :
                    Callback<UserHistoryResponse?> {
                    override fun onResponse(
                        call: Call<UserHistoryResponse?>,
                        response: Response<UserHistoryResponse?>,
                    )  {if (response.isSuccessful() && response.body()!=null) {
                        Log.d("Response: ", response.body().toString())
                        data = response.body()?.data as ArrayList<UserHistoryModel>
                        // This will pass the ArrayList to our Adapter
                        adapter = UserHistoryAdapter(data!!)
                        // Setting the Adapter with the recyclerview
                        recyclerview.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                        else
                    {
                        response.body()?.status?.let { Toast.makeText(applicationContext, it,Toast.LENGTH_SHORT).show() }
                        response.body()?.message?.let { Log.i("response error", it) }
                    }
                    }

                    override fun onFailure(call: Call<UserHistoryResponse?>, t: Throwable) {
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
}