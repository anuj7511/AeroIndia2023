package universal.appfactory.aeroindia2023.delegate

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_delegate_hotel.*
import universal.appfactory.aeroindia2023.R

class DelegateHotelActivity : AppCompatActivity() {
    private lateinit var viewModel : DelegateViewModel
    private lateinit var data : ArrayList<DelegateModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegate_hotel)

        viewModel = ViewModelProvider(this)[DelegateViewModel::class.java]
        viewModel.init((this as  AppCompatActivity).applicationContext as Application)

        viewModel.allDelegates.observe(this){

            Log.d("sizeOfData:",it.size.toString())
            delegate_hotel_name.text = it[0].getHotel_name()
            delegate_hotel_address.text = it[0].getHotel_address()
            delegate_hotel_city.text = it[0].getHotel_city()
            delegate_hotel_contact_number.text = it[0].getHotel_contact()
            delegate_hotel_rating.text = it[0].getStar_rating()
        }
    }
}