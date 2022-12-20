package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView


class HomepageActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        supportActionBar?.hide()

        val bundle = intent.extras
        findViewById<TextView>(R.id.userNameView).text = "Welcome, " + bundle?.getString("username", "DEFAULT USER")

    }

    fun iconClicked(view: View) {
        val tag = view.getTag().toString()
        val bundle = intent.extras

        Log.i("Clicked Button tag", tag)

        startActivity(intent)

        var navigateIntent: Intent = Intent(this@HomepageActivity,  DummyActivity::class.java)
        when(tag.toInt()){
            0 -> {navigateIntent = Intent(this@HomepageActivity, AgendaActivity::class.java)}       // Agenda
            1 -> {navigateIntent = Intent(this@HomepageActivity, SpeakersActivity::class.java)}     // Speakers
            2 -> {navigateIntent = Intent(this@HomepageActivity, MapsActivity::class.java)}         // Venue maps
            3 -> {navigateIntent = Intent(this@HomepageActivity, DummyActivity::class.java)}        // Driving directions
            4 -> {navigateIntent = Intent(this@HomepageActivity, ExhibitorsActivity::class.java)}   // Resources
            5 -> {navigateIntent = Intent(this@HomepageActivity, DummyActivity::class.java)}        // Videos
            6 -> {navigateIntent = Intent(this@HomepageActivity, DummyActivity::class.java)}        // Twitter
            7 -> {navigateIntent = Intent(this@HomepageActivity, AgendaActivity::class.java)}       // FAQ
            8 -> {navigateIntent = Intent(this@HomepageActivity, Feedback::class.java)}             // Lodging complaints
            else -> {
                Log.i("Homepage msg", "Nothing was clicked")
            }
        }

        navigateIntent.putExtras(bundle!!)
        startActivity(navigateIntent)

    }
}