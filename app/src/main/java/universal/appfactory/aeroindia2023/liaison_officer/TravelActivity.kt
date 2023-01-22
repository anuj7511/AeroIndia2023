package universal.appfactory.aeroindia2023.liaison_officer

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_travel.*
import kotlinx.android.synthetic.main.activity_vehicle.*
import universal.appfactory.aeroindia2023.R

class TravelActivity : AppCompatActivity() {

    private lateinit var adapter: TravelAdapter
    private lateinit var data: ArrayList<LiaisonModel>
    private lateinit var viewModel: LiaisonViewModel
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        viewModel = ViewModelProvider(this)[LiaisonViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)
        recyclerView = travel_recycler
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        data = ArrayList()

        viewModel.allLiaison.observe(this){
            data = it as ArrayList<LiaisonModel>
            adapter = TravelAdapter(data)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}