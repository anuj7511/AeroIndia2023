package universal.appfactory.aeroindia2023.liaison_officer.trail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import universal.appfactory.aeroindia2023.R

class TrailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trail)

        replaceFragment(TrailFragment(this@TrailActivity))
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.trail_fragment,fragment)
        fragmentTransaction.commit()

    }
}