package universal.appfactory.aeroindia2023.liaison_officer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.travel_view.view.*
import universal.appfactory.aeroindia2023.R

class TravelAdapter(list_of_travel : ArrayList<LiaisonModel>): RecyclerView.Adapter<TravelAdapter.MyViewHolder>() {

    private var listOfTravel : ArrayList<LiaisonModel>
    class MyViewHolder(itemView : View) :  RecyclerView.ViewHolder(itemView) {
        var delegateName = itemView.travel_delegate_name
        var arrivalDate = itemView.delegate_arrival_date
        var arrivalTime = itemView.delegate_arrival_time
        var flightInfo = itemView.delegate_flight_info
        var departureDate = itemView.delegate_departure_date
        var departureTime = itemView.delegate_departure_time
        var departureTerminal = itemView.delegate_departure_terminal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.travel_view,parent,false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var itemViewHolder = listOfTravel[position]

        holder.delegateName.text = itemViewHolder.getDelegate_first_name()+" "+itemViewHolder.getDelegate_last_name()
        holder.arrivalDate.text = itemViewHolder.getArrival_date()
        holder.arrivalTime.text = itemViewHolder.getArrival_time()
        holder.flightInfo.text = itemViewHolder.getFlight_info()
        holder.departureDate.text = itemViewHolder.getDeparture_date()
        holder.departureTime.text = itemViewHolder.getDeparture_time()
        holder.departureTerminal.text = itemViewHolder.getDeparture_terminal()
    }

    override fun getItemCount(): Int {
        return listOfTravel.size
    }

    init{
        this.listOfTravel = list_of_travel
    }
}