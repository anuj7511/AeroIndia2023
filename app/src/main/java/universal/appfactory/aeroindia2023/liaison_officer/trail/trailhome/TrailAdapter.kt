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
    private var lastCheckedPosition = -1

    inner class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        var trialInfo = itemView.trail_info
        var radioButton = itemView.trail_radio_btn
        var mIsChecked = false


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.trail_view,parent,false)
        context = parent.context
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var holderPosition = position
        holder.trialInfo.text = listOfTrail[holderPosition].getStatus().toString()

        holder.radioButton.isChecked = holderPosition == lastCheckedPosition

        holder.radioButton.setOnClickListener {
            lastCheckedPosition = holderPosition
            notifyDataSetChanged()

        }



    }

    override fun getItemCount(): Int {
        return listOfTrail.size
    }

    init{
        this.listOfTrail =list_of_trail
    }

    fun getTrailResponse() : TrailModel{
        return listOfTrail[lastCheckedPosition]
    }

}