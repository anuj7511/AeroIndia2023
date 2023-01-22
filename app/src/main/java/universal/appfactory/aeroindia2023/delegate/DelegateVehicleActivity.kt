package universal.appfactory.aeroindia2023.delegate

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_assigned_loactivity.*
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

class DelegateVehicleActivity : AppCompatActivity() {
    private lateinit var viewModel: DelegateViewModel
    private lateinit var data : ArrayList<DelegateModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegate_vehical)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)

        viewModel = ViewModelProvider(this)[DelegateViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)

        fetchVehicleData()
//        viewModel.allDelegates.observe(this){
//            data = it as ArrayList<DelegateModel>
//            var item = data[0]
//            Vehicle_number.text = item.getVehicle_number()
//            vehical_color.text = item.getVehicle_colour()
//            drive_name.text = item.getDriver_name()
//            driver_contact_number.text = item.getDriver_mobile_number()
//        }


    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchVehicleData() {
        val delegateApi = ApiClient.getInstance().create(ApiInterface::class.java)

        GlobalScope.launch(Dispatchers.IO) {

            delegateApi.getDelegates("Bearer 61b25a411a2dad66bb7b6ff145db3c2f",1)?.enqueue(
                object : Callback<DelegateResponse?> {
                    override fun onResponse(
                        call: Call<DelegateResponse?>,
                        response: Response<DelegateResponse?>
                    ) {
                        data = response.body()?.data as ArrayList<DelegateModel>
                        var item = data[0]
                        Vehicle_number.text = item.getVehicle_number()
                        vehical_color.text = item.getVehicle_colour()
                        drive_name.text = item.getDriver_name()
                        driver_contact_number.text = item.getDriver_mobile_number()
                    }

                    override fun onFailure(call: Call<DelegateResponse?>, t: Throwable) {
                        Log.d("Delegate Failure Response: ", t.message.toString())
                    }

                }
            )

        }
    }
}