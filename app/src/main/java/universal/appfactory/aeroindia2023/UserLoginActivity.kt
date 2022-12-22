package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class UserLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        val loginButtonId = findViewById<Button>(R.id.loginButton)
        val signUpButtonId = findViewById<Button>(R.id.signUpButton)

        loginButtonId.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailAddress).text.toString()
        }

        signUpButtonId.setOnClickListener{
            val intent = Intent(this@UserLoginActivity, UserRegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}