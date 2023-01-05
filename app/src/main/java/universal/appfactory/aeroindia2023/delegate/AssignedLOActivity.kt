package universal.appfactory.aeroindia2023.delegate

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_assigned_loactivity.*
import universal.appfactory.aeroindia2023.R

class AssignedLOActivity : AppCompatActivity() {
    private lateinit var viewModel: DelegateViewModel
    private lateinit var data: ArrayList<DelegateModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assigned_loactivity)

        viewModel = ViewModelProvider(this)[DelegateViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)

        viewModel.allDelegates.observe(this){
            data = it as ArrayList<DelegateModel>
            var item = data[0]
            lo_name.text = item.getLiason_ooficer()
            lo_gmail.text = item.getLO_email()
            lo_contact_number.text  = item.getLO_mobile()
            lo_organization.text = item.getLO_organisation()

        }
    }
}