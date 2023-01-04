package universal.appfactory.aeroindia2023.delegate

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.vehicle_view.*
import universal.appfactory.aeroindia2023.R

class DelegateVehicleActivity : AppCompatActivity() {
    private lateinit var viewModel: DelegateViewModel
    private lateinit var data : ArrayList<DelegateModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegate_vehical)

        viewModel = ViewModelProvider(this)[DelegateViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)

        viewModel.allDelegates.observe(this){
            data = it as ArrayList<DelegateModel>
            var item = data[0]
            Vehicle_number.text = item.getVehicle_number()
            vehical_color.text = item.getVehicle_colour()
            drive_name.text = item.getDriver_name()
            driver_contact_number.text = item.getDiver_mobile_number()
        }


    }
}