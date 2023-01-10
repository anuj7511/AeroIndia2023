package universal.appfactory.aeroindia2023.liaison_officer

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
import kotlinx.android.synthetic.main.fragment_delegate.*
import kotlinx.android.synthetic.main.fragment_delegate.view.*
import universal.appfactory.aeroindia2023.R


class DelegateFragment(private var mContext: Context) : Fragment() {

    private lateinit var adapter: DelegationAdapter
    private lateinit var data : ArrayList<LiaisonModel>
    private lateinit var viewModel: LiaisonViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this)[LiaisonViewModel::class.java]
        viewModel.init((mContext as AppCompatActivity).applicationContext as Application)

        var binding = inflater.inflate(R.layout.fragment_delegate, container, false)
        recyclerView = binding.delegation_recycler
        recyclerView.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)

        data = ArrayList()
        viewModel.allLiaison.observe(viewLifecycleOwner){
            data = it as ArrayList<LiaisonModel>
            adapter = DelegationAdapter(data)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        return binding.rootView






    }


}