package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ManagerAdapter (private val pList: ArrayList<ManagerModel>) : RecyclerView.Adapter<ManagerAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.manager_user_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // setting data to our views of recycler view.
        val itemsViewModel = pList[position]
        // sets the text to the textview from our itemHolder class
        holder.manager.text=itemsViewModel.getManager().trim()
        holder.nameText.text = itemsViewModel.getName().trim()
        holder.remarks.text = itemsViewModel.getremarks().trim()
        holder.date_Time.text = itemsViewModel.getdate_time().trim()

        if(itemsViewModel.getstatus() == 1)
            holder.status.text = "Pending"
        else
            holder.status.text = "Resolved"
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return pList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var manager:TextView=itemView.findViewById(R.id.mgr)
        var nameText: TextView = itemView.findViewById(R.id.user)
        var remarks: TextView = itemView.findViewById(R.id.r)
        var date_Time: TextView = itemView.findViewById(R.id.date_time)
        var status:TextView=itemView.findViewById(R.id.Rstatus)

    }
}