package universal.appfactory.aeroindia2023.liaison_officer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_delegation_info.*
import universal.appfactory.aeroindia2023.R

class DelegationInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegation_info)

        var email = intent.getStringExtra("email")
        var contact_number = intent.getStringExtra("delegateContactNumber")
        var hotel_address = intent.getStringExtra("hotel_address")

        delegation_email.text = email.toString()
        delegation_contact_number.text = contact_number.toString()
        delegation_hotel_address.text = hotel_address.toString()
    }
}