package universal.appfactory.aeroindia2023

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class ExhibitorAdapter(mList: ArrayList<ExhibitorModel>, private val mContext: Context) : RecyclerView.Adapter<ExhibitorAdapter.ViewHolder>() {

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
        val name = itemsViewModel.getFirstName() + " " + itemsViewModel.getLastName()

        // sets the text to the textview from our itemHolder class
        holder.nameText.text = name
        holder.countryText.text = itemsViewModel.getAddress()

        holder.exhibitorCard.setOnClickListener{
            val intent = Intent(mContext,SelectedExhibitorActivity::class.java)
            intent.putExtra("Name",name)
            intent.putExtra("Image",itemsViewModel.getExhibitorLogo())
            intent.putExtra("Country",itemsViewModel.getCountry())
            intent.putExtra("Email",itemsViewModel.getEmail())
            intent.putExtra("Address",itemsViewModel.getAddress())
            intent.putExtra("Mobile",itemsViewModel.getMobile())
            intent.putExtra("Website",itemsViewModel.getCompanyWebsite())
            intent.putExtra("Company",itemsViewModel.getCompanyName())
            mContext.startActivity(intent)
        }

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
        var exhibitorCard: CardView = itemView.findViewById(R.id.exhibitor_card)
        var locationText: TextView = itemView.findViewById(R.id.location)

    }

    // creating a constructor for our variables.
    init {
        this.exhibitorModelArrayList = mList
    }

}