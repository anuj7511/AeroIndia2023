package universal.appfactory.aeroindia2023.products

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
import kotlin.collections.ArrayList

class ProductsActivity : AppCompatActivity() {

    // variable for our adapter
    // class and array list
    private lateinit var adapter: ProductAdapter
    private lateinit var data: ArrayList<ProductModel>
    private lateinit var recyclerview: RecyclerView
    private lateinit var viewModel: ProductViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var category: String
    private lateinit var name: String
    private lateinit var lastCategory: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

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
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)

        category = intent.getStringExtra("Category").toString()
        name = intent.getStringExtra("Name").toString()
        lastCategory = intent.getStringExtra("Last Category").toString()

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        data = ArrayList()
        setUpProducts()


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
            viewModel.loadAllProducts(true)
            setUpProducts()
            refreshView.isRefreshing = false
        }
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredList = ArrayList<ProductModel>()
        val newData = when (category) {
            "second" -> data.distinctBy { it.getSecond_categoy() } as ArrayList<ProductModel>
            "third" -> data.distinctBy { it.getThird_categoy() } as ArrayList<ProductModel>
            "last" -> data
            else -> data.distinctBy { it.getFirst_category() } as ArrayList<ProductModel>
        }

        // running a for loop to compare elements.
        for (item in newData) {
            // checking if the entered string matched with any item of our recycler view.
            when (category) {
                "second" -> {
                    if (item.getSecond_categoy().lowercase(Locale.ROOT)
                            .contains(text.lowercase(Locale.getDefault()))
                    ) {
                        filteredList.add(item)
                    }
                }
                "third" -> {
                    if (item.getThird_categoy().lowercase(Locale.ROOT)
                            .contains(text.lowercase(Locale.getDefault()))
                    ) {
                        filteredList.add(item)
                    }
                }
                "last" -> {
                    if (item.getProduct_title().lowercase(Locale.ROOT)
                            .contains(text.lowercase(Locale.getDefault()))
                    ) {
                        filteredList.add(item)
                    }
                }
                else -> {
                    if (item.getFirst_category().lowercase(Locale.ROOT)
                            .contains(text.lowercase(Locale.getDefault()))
                    ) {
                        filteredList.add(item)
                    }
                }
            }
        }
        adapter.filterList(filteredList)
    }

    private class SortByName : Comparator<ProductModel> {
        override fun compare(
            object1: ProductModel,
            object2: ProductModel
        ): Int {
            val name1: String = object1.getProduct_title().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getProduct_title().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }

    private class SortByFirstCategory : Comparator<ProductModel> {
        override fun compare(
            object1: ProductModel,
            object2: ProductModel
        ): Int {
            val name1: String = object1.getFirst_category().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getFirst_category().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }

    private class SortBySecondCategory : Comparator<ProductModel> {
        override fun compare(
            object1: ProductModel,
            object2: ProductModel
        ): Int {
            val name1: String = object1.getSecond_categoy().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getSecond_categoy().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }

    private class SortByThirdCategory : Comparator<ProductModel> {
        override fun compare(
            object1: ProductModel,
            object2: ProductModel
        ): Int {
            val name1: String = object1.getThird_categoy().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getThird_categoy().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }

    private fun setUpProducts() {
        viewModel.allproduct.observe(this) {
            data = it as ArrayList<ProductModel>
            val tempList : ArrayList<ProductModel> = ArrayList()
            when (category) {
                "second" -> {
                    for(item in data)
                    {
                        if(item.getFirst_category() == name)
                            tempList.add(item)
                    }
                    data = tempList
                    Collections.sort(data, SortBySecondCategory())
                }
                "third" -> {
                    for(item in data)
                    {
                        if(item.getSecond_categoy() == name)
                            tempList.add(item)
                    }
                    data = tempList
                    Collections.sort(data, SortByThirdCategory())
                }
                "last" -> {
                    for(item in data)
                    {
                        when(lastCategory) {
                            "second" -> {
                                if(item.getSecond_categoy() == name)
                                    tempList.add(item)
                            }
                            "third" -> {
                                if(item.getThird_categoy() == name)
                                    tempList.add(item)
                            }
                            else -> {
                                if(item.getFirst_category() == name)
                                    tempList.add(item)
                            }
                        }
                    }
                    data = tempList
                    Collections.sort(data, SortByName())
                }
                else -> Collections.sort(data, SortByFirstCategory())
            }
            // This will pass the ArrayList to our Adapter
            adapter = ProductAdapter(data, this@ProductsActivity, category)
            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter
            progressBar.visibility = View.INVISIBLE
        }
    }
}