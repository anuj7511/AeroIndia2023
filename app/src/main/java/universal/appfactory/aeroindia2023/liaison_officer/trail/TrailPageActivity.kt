package universal.appfactory.aeroindia2023.liaison_officer.trail

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_trail.*
import kotlinx.android.synthetic.main.activity_trail_page.*
import kotlinx.android.synthetic.main.fragment_delegate.view.*
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.liaison_officer.DelegationAdapter
import universal.appfactory.aeroindia2023.liaison_officer.LiaisonModel
import universal.appfactory.aeroindia2023.liaison_officer.LiaisonViewModel
import universal.appfactory.aeroindia2023.navigableBundle

class TrailPageActivity : AppCompatActivity() {
    private lateinit var adapter: TrailDelegateAdapter
    private lateinit var data : ArrayList<LiaisonModel>
    private lateinit var viewModel: LiaisonViewModel
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trail_page)

        Toast.makeText(this,"trailPage",Toast.LENGTH_SHORT).show()

        navigableBundle = intent.extras!!
        var liaisonId = navigableBundle.getString("foreignKeyId","0")
        var userName = navigableBundle.getString("userName","0")

        viewModel = ViewModelProvider(this)[LiaisonViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)


        recyclerView = trail_delegation_recycler
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        data = ArrayList()
        viewModel.allLiaison.observe(this){
            data = it as ArrayList<LiaisonModel>
            adapter = TrailDelegateAdapter(data,liaisonId,userName)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }

    }
}