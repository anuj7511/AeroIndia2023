package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhistory

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_trail.view.*
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailAdapter
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailModel
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailViewModel


class TrailHistoryFragment(private val mContext : Context) : Fragment() {

    private lateinit var adapter: TrailHistoryAdapter
    private lateinit var data : ArrayList<TrailHistoryModel>
    private lateinit var viewModel: TrailHistoryViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[TrailHistoryViewModel::class.java]
        viewModel.init((mContext as AppCompatActivity).applicationContext as Application)

        val binding =  inflater.inflate(R.layout.fragment_trail, container, false)
        recyclerView = binding.trail_recycler
        recyclerView.layoutManager = LinearLayoutManager(mContext,
            LinearLayoutManager.VERTICAL,false)

        data = ArrayList()

        viewModel.allTrailHistory.observe(viewLifecycleOwner){
            data = it as ArrayList<TrailHistoryModel>
            adapter = TrailHistoryAdapter(data)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        return binding.rootView
    }



}