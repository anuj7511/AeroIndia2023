package universal.appfactory.aeroindia2023.agendas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import universal.appfactory.aeroindia2023.R


class TimeClassificationAdapter(mList: ArrayList<Time>, private val mContext: Context) : RecyclerView.Adapter<TimeClassificationAdapter.ViewHolder>() {

    // creating a variable for array list and context.
    private var agendaModelArrayList: ArrayList<Time>

    // method for filtering our recyclerview items.
    fun filterList(filterList: ArrayList<Time>) {
        // below line is to add our filtered
        // list in our course array list.
        agendaModelArrayList = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.agenda_card2, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // setting data to our views of recycler view.
        val itemsViewModel = agendaModelArrayList[position]

        // sets the text to the textview from our itemHolder class
        holder.name.text = itemsViewModel.getStart_date_time().trim()

        holder.agendaCard.setOnClickListener{
            val intent = Intent(mContext, AgendaActivity::class.java)
            intent.putExtra("Classification","Time")
            intent.putExtra("Time",itemsViewModel.getStart_date_time())
            mContext.startActivity(intent)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return agendaModelArrayList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var name: TextView = itemView.findViewById(R.id.name)
        var agendaCard: CardView = itemView.findViewById(R.id.agenda_card)

    }

    // creating a constructor for our variables.
    init {
        this.agendaModelArrayList = mList
    }

}