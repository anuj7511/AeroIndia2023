package universal.appfactory.aeroindia2023.delegate

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_assigned_loactivity.*
import kotlinx.android.synthetic.main.activity_delegate_travel.*
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

class DelegateTravelActivity : AppCompatActivity() {

    private lateinit var viewModel: DelegateViewModel
    private lateinit var data: ArrayList<DelegateModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegate_travel)

        viewModel = ViewModelProvider(this)[DelegateViewModel::class.java]
        viewModel.init((this as AppCompatActivity).applicationContext as Application)

        fetchTraveldata()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchTraveldata() {
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
                        del_arrival_date.text = item.getArrival_date()
                        del_arrival_time.text = item.getArrival_time()
                        del_flight_info.text = item.getFlight_info()
                        del_departure_date.text = item.getDeparture_date()
                        del_departure_time.text = item.getDeparture_time()
                        del_departure_terminal.text = item.getDeparture_terminal()
                    }

                    override fun onFailure(call: Call<DelegateResponse?>, t: Throwable) {
                        Log.d("Delegate Failure Response: ", t.message.toString())
                    }

                }
            )

        }
    }
}