package universal.appfactory.aeroindia2023.weather

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import universal.appfactory.aeroindia2023.*
import kotlin.collections.ArrayList


class WeatherActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var adapter: WeatherAdapter
    private lateinit var data: ArrayList<TemperatureModel>
    private lateinit var recyclerview: RecyclerView
    private lateinit var viewModel: WeatherViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        textView = findViewById(R.id.temperature)
        recyclerview = findViewById(R.id.recycler_view)
        val refreshView = findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        progressBar = findViewById(R.id.progressBar)
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        data = ArrayList()

        viewModel.allweather.observe(this) {
            data = it as java.util.ArrayList<TemperatureModel>
            var curr = "0"
            if (data.isNotEmpty())
                curr = data[0].getApp_max_temp().toString() + "°"

            textView.text = curr
            // This will pass the ArrayList to our Adapter
            adapter = WeatherAdapter(data)
            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter
            progressBar.visibility = View.INVISIBLE
        }

        refreshView.setOnRefreshListener {
            viewModel.loadAllWeather(true)
            refreshView.isRefreshing = false
        }
    }
}