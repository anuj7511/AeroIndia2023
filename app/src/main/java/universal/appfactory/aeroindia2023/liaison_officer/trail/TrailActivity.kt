package universal.appfactory.aeroindia2023.liaison_officer.trail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailFragment

class TrailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trail)

        val liaisonId: String = intent.getStringExtra("liaisonId")!!
        val userName: String = intent.getStringExtra("userName")!!
        val delegateId: Int = intent.getIntExtra("delegateId", 0)
        replaceFragment(TrailFragment(this@TrailActivity, userName, delegateId, liaisonId.toInt()))
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.trail_fragment,fragment)
        fragmentTransaction.commit()

    }
}