package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import universal.appfactory.aeroindia2023.R

class HomepageActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        supportActionBar?.hide()

        val bundle = intent.extras
        findViewById<TextView>(R.id.userNameView).text = bundle?.getString("username", "DEFAULT USER")

    }

    fun iconClicked(view: View) {
        val tag = view.getTag().toString()
        val bundle = intent.extras

        Log.i("Clicked Button tag", tag)

        startActivity(intent)

        var navigateIntent: Intent = Intent(this@HomepageActivity,  DummyActivity::class.java)
        when(tag.toInt()){
            0 -> {navigateIntent = Intent(this@HomepageActivity, AgendaActivity::class.java)}
            1 -> {navigateIntent = Intent(this@HomepageActivity, SpeakersActivity::class.java)}
            2 -> {navigateIntent = Intent(this@HomepageActivity, MapsActivity::class.java)}
            3 -> {navigateIntent = Intent(this@HomepageActivity, DummyActivity::class.java)}
            4 -> {navigateIntent = Intent(this@HomepageActivity, ExhibitorsActivity::class.java)}
            5 -> {navigateIntent = Intent(this@HomepageActivity, DummyActivity::class.java)}
            6 -> {navigateIntent = Intent(this@HomepageActivity, DummyActivity::class.java)}
            7 -> {navigateIntent = Intent(this@HomepageActivity, AgendaActivity::class.java)}
            8 -> {navigateIntent = Intent(this@HomepageActivity, Feedback::class.java)}
            else -> {
                Log.i("Homepage msg", "Nothing was clicked")
            }
        }

        navigateIntent.putExtras(bundle!!)
        startActivity(navigateIntent)

    }
}