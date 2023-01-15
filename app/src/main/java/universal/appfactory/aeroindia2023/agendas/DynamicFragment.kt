package universal.appfactory.aeroindia2023.agendas

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import universal.appfactory.aeroindia2023.R
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class DynamicFragment(private val classification: String, private val name: String, private val mContext: Context) : Fragment() {

    private lateinit var adapter: AgendaAdapter
    private lateinit var data: ArrayList<AgendaModel>
    private lateinit var recyclerview: RecyclerView
    private lateinit var viewModel: AgendaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_dynamic, container, false)
        initViews(view)

        return view
    }

    // initialise the categories
    private fun initViews(view: View) {
        viewModel = ViewModelProvider(this)[AgendaViewModel::class.java]
        viewModel.init((mContext as AppCompatActivity).applicationContext as Application)
        recyclerview = view.findViewById(R.id.recycler_view)
        val refreshView = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this.context)

        // ArrayList of class ItemsViewModel
        data = ArrayList()
        fetchAgendaData()

        refreshView.setOnRefreshListener{
            viewModel.loadAllAgendas(true)
            refreshView.isRefreshing = false
        }
    }

    companion object {
        fun newInstance(classification: String, name: String, mContext: Context): DynamicFragment {
            return DynamicFragment(classification, name, mContext)
        }
    }

    private fun fetchAgendaData() {
        viewModel.allagenda.observe(viewLifecycleOwner) {
            data = it as ArrayList<AgendaModel>
            val classifiedList = ArrayList<AgendaModel>()

            when (classification) {
                "Category" -> {
                    for (item in data) {
                        if (item.getCategories() == name) {
                            classifiedList.add(item)
                        }
                    }
                    Collections.sort(classifiedList, SortByCategory())
                }
                "Time" -> {
                    for (item in data) {
                        if (item.getStart_date_time() == name) {
                            classifiedList.add(item)
                        }
                    }
                    Collections.sort(classifiedList, SortByTime())
                }
                else -> {
                    for (item in data) {
                        if (item.getLocation_name() == name) {
                            classifiedList.add(item)
                        }
                    }
                    Collections.sort(classifiedList, SortByLocation())
                }
            }
            adapter = AgendaAdapter(classifiedList, mContext)
            recyclerview.adapter = adapter
        }
    }

    private class SortByCategory : Comparator<AgendaModel> {
        override fun compare(
            object1: AgendaModel,
            object2: AgendaModel
        ): Int {
            val name1: String = object1.getCategories().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getCategories().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }

    private class SortByTime : Comparator<AgendaModel> {
        override fun compare(
            object1: AgendaModel,
            object2: AgendaModel
        ): Int {
            val name1: String = object1.getStart_date_time().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getStart_date_time().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }

    private class SortByLocation : Comparator<AgendaModel> {
        override fun compare(
            object1: AgendaModel,
            object2: AgendaModel
        ): Int {
            val name1: String = object1.getLocation_name().lowercase(Locale.ROOT).trim()
            val name2: String = object2.getLocation_name().lowercase(Locale.ROOT).trim()
            return name1.compareTo(name2)
        }
    }

}