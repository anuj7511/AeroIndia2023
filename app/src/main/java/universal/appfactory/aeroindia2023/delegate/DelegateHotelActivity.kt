package universal.appfactory.aeroindia2023.delegate

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            data = it as ArrayList<DelegateModel>
            var item = data[0]
            delegate_hotel_name.text = item.getHotel_name()
            delegate_hotel_address.text = item.getHotel_address()
            delegate_hotel_city.text = item.getHotel_city()
            delegate_hotel_contact_number.text = item.getHotel_contact()
            delegate_hotel_rating.text = item.getStar_rating()
        }
    }
}