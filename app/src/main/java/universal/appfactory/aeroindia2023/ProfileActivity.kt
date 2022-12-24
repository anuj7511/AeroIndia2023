package universal.appfactory.aeroindia2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

var navigableBundle = Bundle()

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        navigableBundle = intent.extras!!
        findViewById<TextView>(R.id.name).text = navigableBundle.getString("name", "DEFAULT USER")
        findViewById<TextView>(R.id.designation).text = navigableBundle.getString("designation", "-NIL-")
    }

    fun iconClicked(view: View){

        val tag = view.getTag().toString()
        var navigableIntent = Intent(this@ProfileActivity, DummyActivity::class.java)

        Log.i("Profile Activity Msg", "userFunction clicked & tag = $tag")

        when(tag.toInt()){
            0 -> { navigableIntent = Intent(this@ProfileActivity, ProfileInfoActivity::class.java) }
            1 -> { navigableIntent = Intent(this@ProfileActivity, ProfileSettingsActivity::class.java) }
            2 -> { navigableIntent = Intent(this@ProfileActivity, DummyActivity::class.java) }
            3 -> { navigableIntent = Intent(this@ProfileActivity, DummyActivity::class.java) }
            else -> {
                Log.i("Profile Activity msg", "Nothing was clicked")
            }
        }
//
//        // TODO: Properties of User needs to be stored in intent before starting activity
        navigableIntent.putExtras(navigableBundle)
        startActivity(navigableIntent)

    }
}