package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import universal.appfactory.aeroindia2023.R

// Main Activity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun submitEmail(view: View){
        val email = findViewById<EditText>(R.id.emailAddress).text
        val username = findViewById<EditText>(R.id.username).text
        val mobileNo = findViewById<EditText>(R.id.mobileNumber).text



        // TODO: OTP activity and API will be invoked to validate the given user ID using OTP
        val intent = Intent(this, OtpActivity::class.java)
        intent.putExtra("email", email)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(intent)

        // TODO: New Activity invoked after validation

        // Sample code-chunk to test for input
        Toast.makeText(this, "Email address: $email", Toast.LENGTH_LONG).show()
        Log.e("User Info", "Name: $username\nMobile number: $mobileNo\nUsername: $username")

    }
}