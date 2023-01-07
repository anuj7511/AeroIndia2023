package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.trail_view.view.*
import universal.appfactory.aeroindia2023.R

class TrailAdapter(list_of_trail : ArrayList<TrailModel>): RecyclerView.Adapter<TrailAdapter.MyViewHolder>() {

    private var listOfTrail : ArrayList<TrailModel>
    private lateinit var context : Context
    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        var trialInfo = itemView.trail_info
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.trail_view,parent,false)
        context = parent.context
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.trialInfo.text = listOfTrail[position].getStatus().toString()
    }

    override fun getItemCount(): Int {
        return listOfTrail.size
    }

    init{
        this.listOfTrail =list_of_trail
    }

}