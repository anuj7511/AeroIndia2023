package universal.appfactory.aeroindia2023.delegate

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_delegate_hotel.*
import kotlinx.android.synthetic.main.vehicle_view.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.ApiClient
import universal.appfactory.aeroindia2023.ApiInterface
import universal.appfactory.aeroindia2023.R

class DelegateHotelActivity : AppCompatActivity() {
    private lateinit var viewModel : DelegateViewModel
    private lateinit var data : ArrayList<DelegateModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegate_hotel)

        viewModel = ViewModelProvider(this)[DelegateViewModel::class.java]
        viewModel.init((this as  AppCompatActivity).applicationContext as Application)

        fetchHotelData()
//        viewModel.allDelegates.observe(this){
//
//            Log.d("sizeOfData:",it.size.toString())
//            delegate_hotel_name.text = it[0].getHotel_name()
//            delegate_hotel_address.text = it[0].getHotel_address()
//            delegate_hotel_city.text = it[0].getHotel_city()
//            delegate_hotel_contact_number.text = it[0].getHotel_contact()
//            delegate_hotel_rating.text = it[0].getStar_rating()
//        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchHotelData() {
        val delegateApi = ApiClient.getInstance().create(ApiInterface::class.java)

        GlobalScope.launch(Dispatchers.IO) {

            delegateApi.getDelegates("Bearer 61b25a411a2dad66bb7b6ff145db3c2f",1)?.enqueue(
                object : Callback<DelegateResponse?> {
                    override fun onResponse(
                        call: Call<DelegateResponse?>,
                        response: Response<DelegateResponse?>
                    ) {
                        data = response.body()?.data as ArrayList<DelegateModel>
                        var it = data[0]
                        delegate_hotel_name.text = it.getHotel_name()
                        delegate_hotel_address.text = it.getHotel_address()
                        delegate_hotel_city.text = it.getHotel_city()
                        delegate_hotel_contact_number.text = it.getHotel_contact()
                        delegate_hotel_rating.text = it.getStar_rating()
                    }

                    override fun onFailure(call: Call<DelegateResponse?>, t: Throwable) {
                        Log.d("Delegate Failure Response: ", t.message.toString())
                    }

                }
            )

        }
    }
}