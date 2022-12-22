package universal.appfactory.aeroindia2023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserHistoryAdapter(private val UList: ArrayList<UserHistoryModel>) : RecyclerView.Adapter<UserHistoryAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // setting data to our views of recycler view.
        val itemsViewModel = UList[position]
        // sets the text to the textview from our itemHolder class
        holder.cdate.text= itemsViewModel.getcreateDate().trim()
        holder.udate.text = itemsViewModel.getupdateDate().trim()
        holder.vstatus.text = itemsViewModel.getverifiedstatus().trim()


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return UList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var udate:TextView = itemView.findViewById(R.id.udatetime)
        var cdate: TextView = itemView.findViewById(R.id.cdatetime)
        var vstatus: TextView = itemView.findViewById(R.id.vstatus)


    }
}