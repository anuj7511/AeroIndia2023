package universal.appfactory.aeroindia2023.speakers

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import universal.appfactory.aeroindia2023.R
import java.util.*

class SpeakersActivity : AppCompatActivity() {

    // variable for our adapter
    // class and array list
    private lateinit var adapter: SpeakersAdapter
    private lateinit var data: ArrayList<SpeakerModel>
    private lateinit var recyclerview: RecyclerView
    private lateinit var viewModel: SpeakerViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speakers)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        // getting the recyclerview by its id
        recyclerview = findViewById(R.id.recycler_view)
        // getting searchview by its id
        val searchView = findViewById<SearchView>(R.id.search_bar)
        val refreshView = findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        progressBar = findViewById(R.id.progressBar)
        viewModel = ViewModelProvider(this)[SpeakerViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        data = ArrayList()
        viewModel.allspeaker.observe(this) {
            data = it as ArrayList<SpeakerModel>
            Collections.sort(data, SortByName())
            // This will pass the ArrayList to our Adapter
            adapter = SpeakersAdapter(data, this@SpeakersActivity)
            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter
            progressBar.visibility = View.INVISIBLE
        }

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
            viewModel.loadAllSpeakers(true)
            refreshView.isRefreshing = false
        }
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredList = ArrayList<SpeakerModel>()

        // running a for loop to compare elements.
        for (item in data) {
            // checking if the entered string matched with any item of our recycler view.
            val name = item.getFirst_name() + " " + item.getLast_name()
            if (name.lowercase(Locale.ROOT).contains(text.lowercase(Locale.getDefault()))) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredList.add(item)
            }
        }

        adapter.filterList(filteredList)
    }

    private class SortByName : Comparator<SpeakerModel> {
        override fun compare(
            object1: SpeakerModel,
            object2: SpeakerModel
        ): Int {
            val name1: String = object1.getFirst_name().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getFirst_name().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }
}