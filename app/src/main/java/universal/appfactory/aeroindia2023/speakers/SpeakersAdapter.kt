package universal.appfactory.aeroindia2023.speakers

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


class SpeakersAdapter(mList: ArrayList<SpeakerModel>, private val mContext: Context) : RecyclerView.Adapter<SpeakersAdapter.ViewHolder>() {

    // creating a variable for array list and context.
    private var speakerModelArrayList: ArrayList<SpeakerModel>

    // method for filtering our recyclerview items.
    fun filterList(filterList: ArrayList<SpeakerModel>) {
        // below line is to add our filtered
        // list in our course array list.
        speakerModelArrayList = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

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
        val itemsViewModel = speakerModelArrayList[position]
        val fullName = itemsViewModel.getSalutation().trim() + itemsViewModel.getFirst_name().trim() + " " + itemsViewModel.getLast_name().trim()
        // sets the text to the textview from our itemHolder class
        holder.nameText.text = fullName
        holder.designationText.text = itemsViewModel.getTitle().trim()
        holder.addressText.text = itemsViewModel.getCompany().trim()

        if(itemsViewModel.getProfile_picture_link() != "")
        {
            Glide.with(mContext).load(itemsViewModel.getProfile_picture_link()).into(holder.image)
        }

        holder.speakerCard.setOnClickListener{
            val intent = Intent(mContext, SelectedSpeakerActivity::class.java)
            intent.putExtra("Id",itemsViewModel.getId())
            intent.putExtra("Name",fullName)
            intent.putExtra("Image",itemsViewModel.getProfile_picture_link())
            intent.putExtra("Biography",itemsViewModel.getBiography())
            mContext.startActivity(intent)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return speakerModelArrayList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var nameText: TextView = itemView.findViewById(R.id.speaker_name)
        var designationText: TextView = itemView.findViewById(R.id.designation)
        var addressText: TextView = itemView.findViewById(R.id.address)
        var speakerCard: CardView = itemView.findViewById(R.id.speaker_card)
        var image: ImageView = itemView.findViewById(R.id.image)
        
    }

    // creating a constructor for our variables.
    init {
        this.speakerModelArrayList = mList
    }

}