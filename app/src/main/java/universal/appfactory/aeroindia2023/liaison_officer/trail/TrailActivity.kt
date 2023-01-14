package universal.appfactory.aeroindia2023.liaison_officer.trail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.zonal_manager_user_card.*
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailFragment

class TrailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trail)

        var liaisonId= intent.getStringExtra("liaisonId")!!
        var userName : String = intent.getStringExtra("userName")!!
        var delegateId = intent.getStringExtra("delegateId")!!
        replaceFragment(TrailFragment(this@TrailActivity,userName,delegateId.toInt(),liaisonId.toInt()))
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.trail_fragment,fragment)
        fragmentTransaction.commit()

    }
}