package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val bundle = intent.extras
        findViewById<TextView>(R.id.name).text = bundle?.getString("username", "DEFAULT USER")
    }

    fun userFunction(view: View){
        val tag: Int = view.tag as Int
        var intent = Intent(this@ProfileActivity, ProfileActivity::class.java)

        when(tag){
            0 -> { intent = Intent(this@ProfileActivity, ProfileInfoActivity::class.java) }
            1 -> { intent = Intent(this@ProfileActivity, ProfileActivity::class.java) }
            2 -> { intent = Intent(this@ProfileActivity, ProfileActivity::class.java) }
            3 -> { intent = Intent(this@ProfileActivity, ProfileActivity::class.java) }
            else -> {
                Log.i("Profile Activity msg", "Nothing was clicked")
            }
        }

        // TODO: Properties of User needs to be stored in intent before starting activity
        startActivity(intent)

    }
}