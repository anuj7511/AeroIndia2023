package universal.appfactory.aeroindia2023.liaison_officer.trail

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_trail.*
import kotlinx.android.synthetic.main.zonal_manager_user_card.*
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhistory.TrailHistoryFragment
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhistory.TrailHistoryViewModel
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailFragment

class TrailActivity : AppCompatActivity() {

    private lateinit var trailHistoryViewModel : TrailHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trail)

        var liaisonId= intent.getStringExtra("liaisonId")!!
        var userName : String = intent.getStringExtra("userName")!!
        var delegateId = intent.getStringExtra("delegateId")!!

        trailHistoryViewModel = ViewModelProvider(this)[TrailHistoryViewModel::class.java]
        trailHistoryViewModel.init((this as AppCompatActivity).applicationContext as Application)
        trailHistoryViewModel.loadAllTrailHistory(true,delegateId.toInt(),"delegate")

        replaceFragment(TrailFragment(this@TrailActivity,userName,delegateId.toInt(),liaisonId.toInt()))
        trail.setOnClickListener {
            replaceFragment(TrailFragment(this@TrailActivity,userName,delegateId.toInt(),liaisonId.toInt()))
        }

        trail_history.setOnClickListener {
            replaceFragment(TrailHistoryFragment(this@TrailActivity))
        }


    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.trail_fragment,fragment)
        fragmentTransaction.commit()

    }
}