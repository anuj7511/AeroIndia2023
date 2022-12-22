package universal.appfactory.aeroindia2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val bundle = intent.extras

        findViewById<TextView>(R.id.name).text = bundle?.getString("username", "DEFAULT USER")
    }
}