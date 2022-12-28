package universal.appfactory.aeroindia2023.agendas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.ApiClient
import universal.appfactory.aeroindia2023.ApiInterface
import universal.appfactory.aeroindia2023.R

class AgendaSortActivity : AppCompatActivity() {

    private lateinit var adapterCategory: CategoryClassificationAdapter
    private lateinit var adapterTime: TimeClassificationAdapter
    private lateinit var adapterLocation: LocationClassificationAdapter
    private lateinit var dataCategory: ArrayList<Categories>
    private lateinit var dataTime: ArrayList<Time>
    private lateinit var dataLocation: ArrayList<Location>
    private lateinit var recyclerview: RecyclerView
    private lateinit var checkedItem: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda_sort)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        // getting the recyclerview by its id
        recyclerview = findViewById(R.id.recycler_view)
        val refreshView = findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        val bOpenAlertDialog = findViewById<ImageButton>(R.id.sort)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        dataCategory = ArrayList()
        dataTime = ArrayList()
        dataLocation = ArrayList()
        fetchAgendaData()

        refreshView.setOnRefreshListener{
            fetchAgendaData()
            refreshView.isRefreshing = false
        }

        checkedItem = intArrayOf(0)

        // handle the button to open the alert dialog with the single item selection when clicked
        bOpenAlertDialog.setOnClickListener {
            // AlertDialog builder instance to build the alert dialog
            val alertDialog = AlertDialog.Builder(this)

            // title of the alert dialog
            alertDialog.setTitle("Choose an Item")

            val listItems = arrayOf("Category", "Time", "Location")


            alertDialog.setSingleChoiceItems(listItems, checkedItem[0]) { dialog, which ->
                checkedItem[0] = which

                // when selected an item the dialog should be closed with the dismiss method
                dialog.dismiss()
                fetchAgendaData()
            }

            // set the negative button if the user is not interested to select or change already selected item
            alertDialog.setNegativeButton("Cancel") { dialog, which -> }

            // create and build the AlertDialog instance with the AlertDialog builder instance
            val customAlertDialog = alertDialog.create()

            // show the alert dialog when the button is clicked
            customAlertDialog.show()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchAgendaData () {
        val agendaApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            if(checkedItem[0]==1)
            {
                agendaApi.getAgendaClassificationTime("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")?.enqueue(object :
                    Callback<TimeResponse?> {
                    override fun onResponse(
                        call: Call<TimeResponse?>,
                        response: Response<TimeResponse?>
                    ) {

                        Log.d("Response: ", response.body().toString())
                        dataTime = response.body()?.data as ArrayList<Time>
                        // This will pass the ArrayList to our Adapter
                        adapterTime = TimeClassificationAdapter(dataTime, this@AgendaSortActivity)
                        // Setting the Adapter with the recyclerview
                        recyclerview.adapter = adapterTime

                    }

                    override fun onFailure(call: Call<TimeResponse?>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message,
                            Toast.LENGTH_SHORT).show()
                        Log.d("Failure Response: ", t.message.toString())
                    }
                })
            }
            else if(checkedItem[0]==2)
            {
                agendaApi.getAgendaClassificationLocation("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")?.enqueue(object :
                    Callback<LocationResponse?> {
                    override fun onResponse(
                        call: Call<LocationResponse?>,
                        response: Response<LocationResponse?>
                    ) {

                        Log.d("Response: ", response.body().toString())
                        dataLocation = response.body()?.data as ArrayList<Location>
                        // This will pass the ArrayList to our Adapter
                        adapterLocation = LocationClassificationAdapter(dataLocation, this@AgendaSortActivity)
                        // Setting the Adapter with the recyclerview
                        recyclerview.adapter = adapterLocation

                    }

                    override fun onFailure(call: Call<LocationResponse?>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message,
                            Toast.LENGTH_SHORT).show()
                        Log.d("Failure Response: ", t.message.toString())
                    }
                })
            }
            else
            {
                agendaApi.getAgendaClassificationCategory("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")?.enqueue(object :
                    Callback<CategoryResponse?> {
                    override fun onResponse(
                        call: Call<CategoryResponse?>,
                        response: Response<CategoryResponse?>
                    ) {

                        Log.d("Response: ", response.body().toString())
                        dataCategory = response.body()?.data as ArrayList<Categories>
                        // This will pass the ArrayList to our Adapter
                        adapterCategory = CategoryClassificationAdapter(dataCategory, this@AgendaSortActivity)
                        // Setting the Adapter with the recyclerview
                        recyclerview.adapter = adapterCategory

                    }

                    override fun onFailure(call: Call<CategoryResponse?>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message,
                            Toast.LENGTH_SHORT).show()
                        Log.d("Failure Response: ", t.message.toString())
                    }
                })
            }

        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }
}