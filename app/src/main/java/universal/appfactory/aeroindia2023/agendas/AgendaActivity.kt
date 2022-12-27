package universal.appfactory.aeroindia2023.agendas

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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
import universal.appfactory.aeroindia2023.speakers.SpeakerModel
import universal.appfactory.aeroindia2023.speakers.SpeakerViewModel
import universal.appfactory.aeroindia2023.speakers.SpeakersAdapter
import java.util.*

class AgendaActivity : AppCompatActivity() {

    // variable for our adapter
    // class and array list
    private lateinit var adapter: AgendaAdapter
    private lateinit var data: ArrayList<AgendaModel>
    private lateinit var recyclerview: RecyclerView
    private lateinit var checkedItem: IntArray
    private lateinit var viewModel: AgendaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        // getting the recyclerview by its id
        recyclerview = findViewById(R.id.recycler_view)
        // getting searchview by its id
        val searchView = findViewById<SearchView>(R.id.search_bar)
        val refreshView = findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        val bOpenAlertDialog = findViewById<ImageView>(R.id.sort)
        viewModel = ViewModelProvider(this)[AgendaViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        data = ArrayList()
        fetchAgendaData()

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText)
                return false
            }
        })


        refreshView.setOnRefreshListener{
            viewModel.loadAllAgendas(true)
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

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredList = ArrayList<AgendaModel>()

        // running a for loop to compare elements.
        for (item in data) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getSession_name().lowercase(Locale.ROOT).contains(text.lowercase(Locale.getDefault()))) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredList.add(item)
            }
        }

        adapter.filterList(filteredList)
    }


    private fun fetchAgendaData () {
        viewModel.allagenda.observe(this) {
            data = it as ArrayList<AgendaModel>
            adapter = AgendaAdapter(data,this@AgendaActivity)

            recyclerview.adapter = adapter

            if(checkedItem[0]==1)
            {
                Collections.sort(data, SortByTime())
            }
            else if(checkedItem[0]==2)
            {
                Collections.sort(data, SortByLocation())
            }
            else
            {
                Collections.sort(data, SortByCategory())
            }
        }
    }


    private class SortByCategory : Comparator<AgendaModel> {
        override fun compare(
            object1: AgendaModel,
            object2: AgendaModel
        ): Int {
            val name1: String = object1.getCategories().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getCategories().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }

    private class SortByTime : Comparator<AgendaModel> {
        override fun compare(
            object1: AgendaModel,
            object2: AgendaModel
        ): Int {
            val name1: String = object1.getStart_date_time().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getStart_date_time().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }

    private class SortByLocation : Comparator<AgendaModel> {
        override fun compare(
            object1: AgendaModel,
            object2: AgendaModel
        ): Int {
            val name1: String = object1.getLocation_name().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getLocation_name().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }
}