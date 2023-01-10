package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhistory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.trail_view.view.*
import universal.appfactory.aeroindia2023.R
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailAdapter
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailModel

class TrailHistoryAdapter(list_of_trail_history : ArrayList<TrailHistoryModel>): RecyclerView.Adapter<TrailHistoryAdapter.MyViewHolder>() {

    private var listOfTrailHistory : ArrayList<TrailHistoryModel>
    private lateinit var context : Context
    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        var trialInfo = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.trail_view,parent,false)
        context = parent.context
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("connect adapter after getting parameters for api")
    }

    override fun getItemCount(): Int {
        return listOfTrailHistory.size
    }

    init{
        this.listOfTrailHistory =list_of_trail_history
    }
}