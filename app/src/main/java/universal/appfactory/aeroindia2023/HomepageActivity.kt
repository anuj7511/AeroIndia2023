package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar


class HomepageActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        this.supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        val navigableBundle = intent.extras!!

        findViewById<TextView>(R.id.userNameView).text = navigableBundle.getString("name", "DEFAULT USER")

    }

    fun iconClicked(view: View) {
        val tag = view.tag.toString()
        val navigableBundle = intent.extras

        Log.i("Clicked Button tag", tag)

        var navigateIntent = Intent(this@HomepageActivity,  DummyActivity::class.java)

        when(tag.toInt()){
            0 -> {navigateIntent = Intent(this@HomepageActivity, AgendaActivity::class.java)}       // Agenda
            1 -> {navigateIntent = Intent(this@HomepageActivity, SpeakersActivity::class.java)}     // Speakers
            2 -> {navigateIntent = Intent(this@HomepageActivity, DummyActivity::class.java)}        // Venue maps
            3 -> {navigateIntent = Intent(this@HomepageActivity, MapsActivity::class.java)}         // Driving directions
            4 -> {navigateIntent = Intent(this@HomepageActivity, ExhibitorsActivity::class.java)}   // Resources
            5 -> {navigateIntent = Intent(this@HomepageActivity, ManagerActivity::class.java)}      // Videos
            6 -> {navigateIntent = Intent(this@HomepageActivity, ProductsActivity::class.java)}     // Twitter or products
            7 -> {navigateIntent = Intent(this@HomepageActivity, AgendaActivity::class.java)}       // FAQ
            8 -> {navigateIntent = Intent(this@HomepageActivity, Feedback::class.java)}             // Lodging complaints
            9 -> {navigateIntent = Intent(this@HomepageActivity, ProfileActivity::class.java)}      // Profile view
            else -> {
                Log.i("Homepage msg", "Nothing was clicked")
            }
        }

        navigateIntent.putExtras(navigableBundle!!)
        startActivity(navigateIntent)

    }

    private fun refreshPage(){
        //TODO: Refresh functionality
    }
}