package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class HomepageActivity : AppCompatActivity() {

    private var backpress: Int = 0
    var navigableBundle = Bundle()

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        supportActionBar?.hide()

        navigableBundle = intent.extras!!
        findViewById<TextView>(R.id.userNameView).text = navigableBundle.getString("name", "DEFAULT USER")

    }

    fun iconClicked(view: View) {
        val tag = view.tag.toString()
        Log.i("Clicked Button tag", tag)

        var navigateIntent = Intent(this@HomepageActivity,  DummyActivity::class.java)

        when(tag.toInt()){
            0 -> {navigateIntent = Intent(this@HomepageActivity, AgendaActivity::class.java)
                    backpress=0}       // Agenda
            1 -> {navigateIntent = Intent(this@HomepageActivity, SpeakersActivity::class.java)
                    backpress=0}     // Speakers
            2 -> {navigateIntent = Intent(this@HomepageActivity, DummyActivity::class.java)
                    backpress=0}        // Venue maps
            3 -> {navigateIntent = Intent(this@HomepageActivity, MapsActivity::class.java)
                    backpress=0}         // Driving directions
            4 -> {navigateIntent = Intent(this@HomepageActivity, ExhibitorsActivity::class.java)
                    backpress=0}   // Resources
            5 -> {navigateIntent = Intent(this@HomepageActivity, DummyActivity::class.java)
                    backpress=0}      // Videos
            6 -> {navigateIntent = Intent(this@HomepageActivity, ProductsActivity::class.java)
                    backpress=0}     // Twitter or products
            7 -> {navigateIntent = Intent(this@HomepageActivity, AgendaActivity::class.java)
                    backpress=0}       // FAQ
            8 -> {navigateIntent = Intent(this@HomepageActivity, Feedback::class.java)
                    backpress=0}             // Lodging complaints
            9 -> {navigateIntent = Intent(this@HomepageActivity, ProfileActivity::class.java)
                    backpress=0}      // Profile view
            else -> {
                Log.i("Homepage msg", "Nothing was clicked")
            }
        }

        navigateIntent.putExtras(navigableBundle)
        startActivity(navigateIntent)

    }

    fun refreshPage(view: View) {
        //TODO: Refresh functionality
        Log.i("Homepage activity message", "Home page refreshed")
        Toast.makeText(this, "Page refreshed", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed(){
        backpress = backpress + 1
        if(backpress > 1){
            finishAffinity()
        }
        else{
            Toast.makeText(this, "Press back once again to exit", Toast.LENGTH_SHORT).show()
        }
    }
}