package universal.appfactory.aeroindia2023.liaison_officer

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_hotel.*
import universal.appfactory.aeroindia2023.R

class HotelActivity : AppCompatActivity() {

    private lateinit var adapter : HotelAdapter
    private lateinit var data : ArrayList<LiaisonModel>
    private lateinit var viewModel: LiaisonViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)

        viewModel = ViewModelProvider(this)[LiaisonViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)
        recyclerView = hotel_recycler
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        data = ArrayList()

        viewModel.allLiaison.observe(this){
            data = it as ArrayList<LiaisonModel>
            adapter = HotelAdapter(data)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}