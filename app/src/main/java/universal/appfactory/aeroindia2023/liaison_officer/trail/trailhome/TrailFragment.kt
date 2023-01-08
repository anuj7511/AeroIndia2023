package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.internal.Objects.ToStringHelper
import kotlinx.android.synthetic.main.fragment_trail.view.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import universal.appfactory.aeroindia2023.ApiInterface
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.ServiceBuilder

class TrailFragment(private val mContext : Context): Fragment() {


    private lateinit var adapter: TrailAdapter
    private lateinit var data : ArrayList<TrailModel>
    private lateinit var viewModel: TrailViewModel
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[TrailViewModel::class.java]
        viewModel.init((mContext as AppCompatActivity).applicationContext as Application)

        val binding =  inflater.inflate(R.layout.fragment_trail, container, false)
        recyclerView = binding.trail_recycler
        recyclerView.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)

        data = ArrayList()

        viewModel.allTrail.observe(viewLifecycleOwner){
            data = it as ArrayList<TrailModel>
            adapter = TrailAdapter(data)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        binding.submit_trial.setOnClickListener {
            var liaisonId = 1
            var delegateId=1
            var status = "reached bengalore"
            var remarks = ""
            sendTrail(liaisonId,delegateId,status,remarks)
        }

        return binding.rootView
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun sendTrail(liaisonId: Int, delegateId: Int, status: String, remarks: String) {
        val sendTrailData = SaveTrailFeedbackModel(liaisonId,delegateId,status,remarks)

        val response = ServiceBuilder.buildService(ApiInterface::class.java)
        GlobalScope.launch (Dispatchers.IO){
            response.saveTrailFeedback(sendTrailData,"Bearer 61b25a411a2dad66bb7b6ff145db3c2f").enqueue(
               object : Callback<SaveTrailFeedbackResponse> {
                   override fun onResponse(
                       call: Call<SaveTrailFeedbackResponse>,
                       response: Response<SaveTrailFeedbackResponse>
                   ) {
                       val responseMsg = response.message().toString()
                       Toast.makeText(mContext,responseMsg,Toast.LENGTH_LONG).show()
                   }

                   override fun onFailure(call: Call<SaveTrailFeedbackResponse>, t: Throwable) {
                       Toast.makeText(mContext,t.toString(),Toast.LENGTH_LONG).show()
                   }

               }
            )
        }

    }


}