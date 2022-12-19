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
        findViewById<TextView>(R.id.userNameView).text =
            bundle?.getString("username", "DEFAULT USER")

    }

    fun iconClicked(view: View) {
        val tag = view.getTag().toString()
        val bundle = intent.extras

        Log.i("Clicked Button tag", tag)

        startActivity(intent)

        when(tag.toInt()){
            0 -> {val intent = Intent(this@HomepageActivity, AgendaActivity::class.java)}
            1 -> {val intent = Intent(this@HomepageActivity, SpeakersActivity::class.java)}
            2 -> {val intent = Intent(this@HomepageActivity, MapsActivity::class.java)}
            3 -> {val intent = Intent(this@HomepageActivity, DummyActivity::class.java)}
            4 -> {val intent = Intent(this@HomepageActivity, DummyActivity::class.java)}
            5 -> {val intent = Intent(this@HomepageActivity, DummyActivity::class.java)}
            6 -> {val intent = Intent(this@HomepageActivity, DummyActivity::class.java)}
            7 -> {val intent = Intent(this@HomepageActivity, AgendaActivity::class.java)}
            8 -> {val intent = Intent(this@HomepageActivity, DummyActivity::class.java)}
            else -> {
                Log.i("Homepage msg", "Nothing was clicked")
            }
        }

        intent.putExtras(bundle!!)
        startActivity(intent)

    }
}