package universal.appfactory.aeroindia2023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class SpeakersAdapter(private val mList: ArrayList<SpeakerModel>) : RecyclerView.Adapter<SpeakersAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.speaker_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // setting data to our views of recycler view.
        val itemsViewModel = mList[position]
        val fullName = itemsViewModel.getSalutation().trim() + itemsViewModel.getFirstName().trim() + " " + itemsViewModel.getLastName().trim()
        // sets the text to the textview from our itemHolder class
        holder.nameText.text = fullName
        holder.designationText.text = itemsViewModel.getTitle().trim()
        holder.addressText.text = itemsViewModel.getCompany().trim()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var nameText: TextView = itemView.findViewById(R.id.speaker_name)
        var designationText: TextView = itemView.findViewById(R.id.designation)
        var addressText: TextView = itemView.findViewById(R.id.address)
        
    }

}