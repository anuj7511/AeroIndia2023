package universal.appfactory.aeroindia2023.agendas

import android.app.Application
import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import universal.appfactory.aeroindia2023.R
import java.util.*

class AgendaActivity : AppCompatActivity() {

    // variable for our adapter
    // class and array list
    private lateinit var adapter: AgendaAdapter
    private lateinit var data: ArrayList<AgendaModel>
    private lateinit var recyclerview: RecyclerView
    private lateinit var viewModel: AgendaViewModel
    private lateinit var classification: String
    private lateinit var name: String

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
        val heading = findViewById<TextView>(R.id.all_exhibit)
        viewModel = ViewModelProvider(this)[AgendaViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        classification = intent.getStringExtra("Classification").toString()
        name = if (classification == "Category") {
            intent.getStringExtra("Category").toString()
        } else if (classification == "Time") {
            intent.getStringExtra("Time").toString()
        } else {
            intent.getStringExtra("Location").toString()
        }

        heading.text = name.trim()

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


        refreshView.setOnRefreshListener {
            viewModel.loadAllAgendas(true)
            refreshView.isRefreshing = false
        }

    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredList = ArrayList<AgendaModel>()

        // running a for loop to compare elements.
        for (item in data) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getSession_name().lowercase(Locale.ROOT)
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredList.add(item)
            }
        }

        adapter.filterList(filteredList)
    }


    private fun fetchAgendaData() {
        viewModel.allagenda.observe(this) {
            data = it as ArrayList<AgendaModel>
            val classifiedList = ArrayList<AgendaModel>()

            if (classification == "Category") {
                for (item in data) {
                    if (item.getCategories() == name) {
                        classifiedList.add(item)
                    }
                }

            } else if (classification == "Time") {
                for (item in data) {
                    if (item.getStart_date_time() == name) {
                        classifiedList.add(item)
                    }
                }

            } else {
                for (item in data) {
                    if (item.getLocation_name() == name) {
                        classifiedList.add(item)
                    }
                }

            }
            adapter = AgendaAdapter(classifiedList, this@AgendaActivity)
            recyclerview.adapter = adapter

        }
    }
}