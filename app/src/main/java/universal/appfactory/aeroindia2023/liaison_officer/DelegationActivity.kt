package universal.appfactory.aeroindia2023.liaison_officer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailFragment

class DelegationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegation)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        replaceFragment(DelegateFragment(this@DelegationActivity))

    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.delegation_fragment,fragment)
        fragmentTransaction.commit()

    }
}