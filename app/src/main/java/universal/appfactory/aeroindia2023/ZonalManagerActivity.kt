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

class ZonalManagerActivity : AppCompatActivity() {
    private lateinit var adapter: ZonalManagerAdapter
    private  var data: ArrayList<ZonalManagerModel>?=null
    private lateinit var recyclerview: RecyclerView
    private lateinit var arrow: ImageButton
    private lateinit var hiddenView: TextView
    private lateinit var cardView: CardView
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
        recyclerview.setHasFixedSize(true)
        // ArrayList of class ItemsViewModel
        data = ArrayList()
        fetchPData()
        val view = layoutInflater.inflate(R.layout.zonal_manager_user_card, null)
        cardView = view.findViewById(R.id.remarkCard)
        arrow = view.findViewById(R.id.arrow_button)
        hiddenView = view.findViewById(R.id.r)
        arrow.setOnClickListener {
            // If the CardView is already expanded, set its visibility
            // to gone and change the expand less icon to expand more.
            if (hiddenView.visibility == View.VISIBLE) {
                // The transition of the hiddenView is carried out by the TransitionManager class.
                // Here we use an object of the AutoTransition Class to create a default transition
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                }
                hiddenView.visibility = View.GONE
                arrow.setImageResource(R.drawable.ic_baseline_expand_more_24)
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                }
                hiddenView.visibility = View.VISIBLE
                arrow.setImageResource(R.drawable.ic_baseline_expand_less_24)
            }

        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchPData () {
        val ZApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            ZApi.getproblem("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")?.enqueue(object :
                Callback<ZonalManagerResponse?> {
                override fun onResponse(
                    call: Call<ZonalManagerResponse?>,
                    response: Response<ZonalManagerResponse?>,
                ) {
                    if (response.isSuccessful() && response.body()!=null)
                    { Log.d("Response: ", response.body().toString())
                    data = response.body()?.data as ArrayList<ZonalManagerModel>
                    // This will pass the ArrayList to our Adapter
                    adapter = ZonalManagerAdapter(data!!)
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

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

}
