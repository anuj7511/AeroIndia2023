package universal.appfactory.aeroindia2023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ExhibitorAdapter(private val mList: ArrayList<ExhibitorModel>) : RecyclerView.Adapter<ExhibitorAdapter.ViewHolder>() {

    // creating a variable for array list and context.
    private var exhibitorModelArrayList: ArrayList<ExhibitorModel>

    // method for filtering our recyclerview items.
    fun filterList(filterList: ArrayList<ExhibitorModel>) {
        // below line is to add our filtered
        // list in our course array list.
        exhibitorModelArrayList = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exhibitors_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // setting data to our views of recycler view.
        val itemsViewModel = exhibitorModelArrayList[position]
        val location = itemsViewModel.getHallNo() + ", Stall " + itemsViewModel.getStallNo()

        // sets the text to the textview from our itemHolder class
        holder.nameText.text = itemsViewModel.getName()
        holder.countryText.text = itemsViewModel.getAddress()
        holder.locationText.text = location

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return exhibitorModelArrayList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var nameText: TextView = itemView.findViewById(R.id.exhibitor_name)
        var countryText: TextView = itemView.findViewById(R.id.country)
        var locationText: TextView = itemView.findViewById(R.id.location)

    }

    // creating a constructor for our variables.
    init {
        this.exhibitorModelArrayList = mList
    }

}