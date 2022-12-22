package universal.appfactory.aeroindia2023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ZonalManagerAdapter(private val ZList: ArrayList<ZonalManagerModel>) : RecyclerView.Adapter<ZonalManagerAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.zonal_manager_user_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // setting data to our views of recycler view.
        val itemsViewModel = ZList[position]
        // sets the text to the textview from our itemHolder class
        holder.nameText.text = itemsViewModel.getName().trim()
        holder.Mname.text= itemsViewModel.getMname().trim()
        holder.remarks.text = itemsViewModel.getremarks().trim()
        holder.date_Time.text = itemsViewModel.getdate_time().trim()
        holder.status.text = itemsViewModel.getstatus().trim()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return ZList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var nameText: TextView = itemView.findViewById(R.id.user)
        var Mname: TextView = itemView.findViewById(R.id.Mname)
        var remarks: TextView = itemView.findViewById(R.id.remark)
        var date_Time: TextView = itemView.findViewById(R.id.date_time)
        var status: TextView = itemView.findViewById(R.id.status)


    }
}