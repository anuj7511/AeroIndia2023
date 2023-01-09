package universal.appfactory.aeroindia2023.liaison_officer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.hotel_view.view.*
import universal.appfactory.aeroindia2023.R

class HotelAdapter(list_of_hotels : ArrayList<LiaisonModel>): RecyclerView.Adapter<HotelAdapter.MyViewHolder>() {

    private var listOfHotels : ArrayList<LiaisonModel>
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var delegateName = itemView.delegate_name
        var hotelName = itemView.hotel_name
        var contactNumber = itemView.hotel_contact_number
        var hotelRating = itemView.hotel_rating
        var hotel_address = itemView.hotel_address
        var hotel_city = itemView.hotel_city
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.hotel_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemsViewModel = listOfHotels[position]
        holder.delegateName.text = itemsViewModel.getDelegate_first_name().toString()+itemsViewModel.getDelegate_last_name().toString()
        holder.hotelName.text = itemsViewModel.getHotel_name()
        holder.contactNumber.text = itemsViewModel.getHotel_contact()
        holder.hotelRating.text = itemsViewModel.getStar_rating()
        holder.hotel_address.text = itemsViewModel.getHotel_address()
        holder.hotel_city.text = itemsViewModel.getHotel_city()
    }

    override fun getItemCount(): Int {
        return listOfHotels.size
    }

    init {
        this.listOfHotels= list_of_hotels
    }

}
