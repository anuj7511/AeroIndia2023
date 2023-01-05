package universal.appfactory.aeroindia2023.exhibitors

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import universal.appfactory.aeroindia2023.R


class ExhibitorAdapter2(mList: ArrayList<ExhibitorModel2>, private val mContext: Context) : RecyclerView.Adapter<ExhibitorAdapter2.ViewHolder>() {

    // creating a variable for array list and context.
    private var exhibitorModelArrayList: ArrayList<ExhibitorModel2>

    // method for filtering our recyclerview items.
    fun filterList(filterList: ArrayList<ExhibitorModel2>) {
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
        val name = itemsViewModel.getFirstname() + " " + itemsViewModel.getLastname()

        // sets the text to the textview from our itemHolder class
        holder.nameText.text = itemsViewModel.getComp_Name()
        holder.countryText.text = itemsViewModel.getAddress()
        holder.locationText.text = itemsViewModel.getHall_and_Stall_Number()

        if(itemsViewModel.getLogo() != "")
        {
            Glide.with(mContext).load(itemsViewModel.getLogo()).into(holder.image)
        }

        holder.exhibitorCard.setOnClickListener{
            val intent = Intent(mContext, SelectedExhibitorActivity::class.java)
            intent.putExtra("Id",itemsViewModel.getId())
            intent.putExtra("Name",name)
            intent.putExtra("Image",itemsViewModel.getLogo())
            intent.putExtra("Country",itemsViewModel.getCountry())
            intent.putExtra("Email",itemsViewModel.getEmail())
            intent.putExtra("Address",itemsViewModel.getAddress())
            intent.putExtra("Mobile",itemsViewModel.getMobile())
            intent.putExtra("Website",itemsViewModel.getWebsite())
            intent.putExtra("Company",itemsViewModel.getComp_Name())
            intent.putExtra("Location",itemsViewModel.getHall_and_Stall_Number())
            intent.putExtra("Description",itemsViewModel.getCompany_Brief())
            intent.putExtra("Company Email",itemsViewModel.getEmail())
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
        var image: ImageView = itemView.findViewById(R.id.image)

    }

    // creating a constructor for our variables.
    init {
        this.exhibitorModelArrayList = mList
    }

}