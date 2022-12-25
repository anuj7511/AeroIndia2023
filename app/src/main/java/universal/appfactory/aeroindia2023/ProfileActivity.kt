package universal.appfactory.aeroindia2023

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder

var navigableBundle = Bundle()

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        navigableBundle = intent.extras!!
        findViewById<TextView>(R.id.name).text = navigableBundle.getString("name", "DEFAULT USER")
        findViewById<TextView>(R.id.designation).text = navigableBundle.getString("designation", "-NIL-")
    }

    fun iconClicked(view: View){

        var passIn = true
        val tag = view.tag.toString()
        var navigableIntent = Intent(this@ProfileActivity, DummyActivity::class.java)

        Log.i("Profile Activity Msg", "userFunction clicked & tag = $tag")

        when(tag.toInt()){
            0 -> { navigableIntent = Intent(this@ProfileActivity, ProfileInfoActivity::class.java) }
            1 -> { navigableIntent = Intent(this@ProfileActivity, ProfileSettingsActivity::class.java) }
            2 -> { userSignOut()
                    passIn = false
                 }
            3 -> { navigableIntent = Intent(this@ProfileActivity, DummyActivity::class.java) }
            else -> {
                Log.i("Profile Activity msg", "Nothing was clicked")
            }
        }
//
        if(passIn) {
            navigableIntent.putExtras(navigableBundle)
            startActivity(navigableIntent)
        }

    }

    private fun userSignOut(){
        val sharedPreferences: SharedPreferences = getSharedPreferences("LocalUserData", MODE_PRIVATE)

        MaterialAlertDialogBuilder(this)
            .setTitle("WARNING !")
            .setMessage("Do you want to sign out for sure ? You'll be logged out of the application as well.")
            .setPositiveButton("Yes"){
                dialog, which -> Log.i("Positive dialog message", "Entered positive dialog content")
                sharedPreferences.edit().clear().apply()
                Log.i("Positive dialog msg", "Local user data cleared & exiting positive dialog message")
                finishAffinity()
            }
            .setNegativeButton("No"){
                dialog, which -> dialog.cancel()
                Log.i("Negative dialog msg", "Exiting negative dialog message")
            }
            .show()

    }

    fun refreshPage(view: View) {
        //TODO: Refresh functionality
        Log.i("Profile activity message", "Profile page refreshed")
    }
}
