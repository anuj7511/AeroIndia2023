package universal.appfactory.aeroindia2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import datshin.appfactory.aeroindia2023.R

// Main Activity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun submitEmail(view: View){
        val email = findViewById<EditText>(R.id.emailAddress).text
        Toast.makeText(this, "Email address: $email", Toast.LENGTH_LONG).show()
    }
}