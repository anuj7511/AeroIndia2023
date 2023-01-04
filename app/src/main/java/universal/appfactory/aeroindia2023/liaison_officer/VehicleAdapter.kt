package universal.appfactory.aeroindia2023.liaison_officer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.hotel_view.view.*
import kotlinx.android.synthetic.main.hotel_view.view.delegate_name
import kotlinx.android.synthetic.main.vehicle_view.view.*
import universal.appfactory.aeroindia2023.R

class VehicleAdapter (list_of_vehicles : ArrayList<LiaisonModel>) : RecyclerView.Adapter<VehicleAdapter.MyViewModel>() {

    private var listOfVehicles : ArrayList<LiaisonModel>
    class MyViewModel(itemView: View): RecyclerView.ViewHolder(itemView) {
        var delegateName = itemView.delegate_name
        var vehicleNumber = itemView.Vehicle_number
        var vehicleColor = itemView.vehical_color
        var driverName = itemView.drive_name
        var driverContactNumber = itemView.driver_contact_number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_view,parent,false)
        return MyViewModel(view)
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
        var itemsViewModel = listOfVehicles[position]
        holder.delegateName.text = itemsViewModel.getDelegate_first_name()+" "+itemsViewModel.getDelegate_last_name()
        holder.vehicleNumber.text = itemsViewModel.getVehicle_number()
        holder.vehicleColor.text = itemsViewModel.getVehicle_colour()
        holder.driverName.text = itemsViewModel.getDriver_name()
        holder.driverContactNumber.text = itemsViewModel.getDriver_mobile_number()
    }

    override fun getItemCount(): Int {
        return listOfVehicles.size
    }

    init {
        this.listOfVehicles = list_of_vehicles
    }

}
