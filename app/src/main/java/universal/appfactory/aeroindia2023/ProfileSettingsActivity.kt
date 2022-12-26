package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class ProfileSettingsActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_settings)

        this.supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        val permissionSwitch = findViewById<Switch>(R.id.blockPermission)
        val notificationSwitch = findViewById<Switch>(R.id.pushNotify)

        val permissionValue = permissionSwitch.isChecked
        val notificationValue = notificationSwitch.isChecked

    }
}