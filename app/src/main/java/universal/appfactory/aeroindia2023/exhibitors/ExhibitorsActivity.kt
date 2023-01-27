package universal.appfactory.aeroindia2023.exhibitors

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.ApiClient
import universal.appfactory.aeroindia2023.ApiInterface
import universal.appfactory.aeroindia2023.R
import java.lang.reflect.Type
import java.util.*


class ExhibitorsActivity : AppCompatActivity() {

    // variable for our adapter
    // class and array list
    private lateinit var adapter: ExhibitorAdapter
    private lateinit var data: ArrayList<ExhibitorModel>
    private lateinit var recyclerview: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exhibitors)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        // getting the recyclerview by its id
        recyclerview = findViewById(R.id.recycler_view)
        // getting searchView by its id
        val searchView = findViewById<SearchView>(R.id.search_bar)
        val refreshView = findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        progressBar = findViewById(R.id.progressBar)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        data = ArrayList()

        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("exhibitors", "")
        val type: Type = object : TypeToken<ArrayList<ExhibitorModel?>?>() {}.type
        if (json != "")
            data = gson.fromJson<Any>(json, type) as ArrayList<ExhibitorModel>

        if (data.isNotEmpty()) {
            Collections.sort(data, SortByName())
            adapter = ExhibitorAdapter(data, this@ExhibitorsActivity)
            recyclerview.adapter = adapter
            progressBar.visibility = View.INVISIBLE
        } else
            data = ArrayList()

        if (data.isEmpty())
            fetchExhibitorData()

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
            fetchExhibitorData()
            refreshView.isRefreshing = false
        }


    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredList = ArrayList<ExhibitorModel>()

        // running a for loop to compare elements.
        for (item in data) {
            // checking if the entered string matched with any item of our recycler view.
            if(!item.getComp_Name().isNullOrEmpty())
            {
                if (item.getComp_Name().lowercase(Locale.ROOT)
                        .contains(text.lowercase(Locale.getDefault()))
                ) {
                    filteredList.add(item)
                }
            }
        }
        adapter.filterList(filteredList)
    }

    private fun fetchExhibitorData() {
        val exhibitorApi = ApiClient.getInstance().create(ApiInterface::class.java)

        // launching a new coroutine
        lifecycleScope.launch(Dispatchers.IO) {

            exhibitorApi.getExhibitors("Bearer 61b25a411a2dad66bb7b6ff145db3c2f")?.enqueue(object :
                Callback<ExhibitorResponse?> {
                override fun onResponse(
                    call: Call<ExhibitorResponse?>,
                    response: Response<ExhibitorResponse?>
                ) {

                    Log.d("Response: ", response.body().toString())
                    data = response.body()?.data as ArrayList<ExhibitorModel>
                    Collections.sort(data, SortByName())
                    saveToSharedPreferences(data)
                    // This will pass the ArrayList to our Adapter
                    adapter = ExhibitorAdapter(data, this@ExhibitorsActivity)
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter
                    progressBar.visibility = View.INVISIBLE
                }

                override fun onFailure(call: Call<ExhibitorResponse?>, t: Throwable) {
                    Toast.makeText(
                        applicationContext, t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("Failure Response: ", t.message.toString())
                }
            })
        }
    }

    private class SortByName : Comparator<ExhibitorModel> {
        override fun compare(
            object1: ExhibitorModel,
            object2: ExhibitorModel
        ): Int {
            var name1 = ""
            var name2 = ""
            if (!object1.getComp_Name().isNullOrEmpty())
                name1 = object1.getComp_Name().lowercase(Locale.ROOT).trim()
            if (!object2.getComp_Name().isNullOrEmpty())
                name2 = object2.getComp_Name().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }

    private fun saveToSharedPreferences(exhibitorsList: ArrayList<ExhibitorModel>) {

        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(exhibitorsList)
        editor.putString("exhibitors", json)
        editor.apply()
    }
}