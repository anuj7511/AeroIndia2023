package universal.appfactory.aeroindia2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class ProfileInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        val navigableBundle = intent.extras

        findViewById<EditText>(R.id.salutation).setText(navigableBundle?.getString("salutation", "NA"))
        findViewById<EditText>(R.id.userName).setText(navigableBundle?.getString("name", "NA"))
        findViewById<EditText>(R.id.userJobTitle).setText(navigableBundle?.getString("designation", "NA"))
        findViewById<EditText>(R.id.userCompanyName).setText(navigableBundle?.getString("userCompany", "NA"))
        findViewById<EditText>(R.id.userEmailAddress).setText(navigableBundle?.getString("email", "NA"))

    }
}