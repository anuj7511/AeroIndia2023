package universal.appfactory.aeroindia2023

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.databinding.ActivityFeedbackBinding
import universal.appfactory.aeroindia2023.databinding.ActivityZonalManagerBinding
import kotlin.properties.Delegates

class ZonalManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityZonalManagerBinding
    private lateinit var adapter: ZonalManagerAdapter
    private  var data: ArrayList<ZonalManagerModel>?=null
    private lateinit var recyclerview: RecyclerView
    private  var zfid:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zonal_manager)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        recyclerview = findViewById<RecyclerView>(R.id.recycler_view)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        // ArrayList of class ItemsViewModel
        data = ArrayList()
        binding = ActivityZonalManagerBinding.inflate(layoutInflater)
        val view = binding.root
        val bundle=intent.extras
        zfid = bundle!!.getString("foreignKeyId","0").toInt()
        Log.i("Foreign Key Id",zfid.toString())


     fetchPData()

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchPData () {
        val ZApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO) {

            ZApi.getproblem("Bearer 61b25a411a2dad66bb7b6ff145db3c2f",zfid)?.enqueue(object :
                Callback<ZonalManagerResponse?> {
                override fun onResponse(
                    call: Call<ZonalManagerResponse?>,
                    response: Response<ZonalManagerResponse?>,
                ) {
                    if (response.isSuccessful() && response.body()!=null)

                    { Log.d("Response: ", response.body().toString())
                    data = response.body()?.data as ArrayList<ZonalManagerModel>


                    // This will pass the ArrayList to our Adapter
                    adapter = ZonalManagerAdapter(data!!,this@ZonalManagerActivity)
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

}
