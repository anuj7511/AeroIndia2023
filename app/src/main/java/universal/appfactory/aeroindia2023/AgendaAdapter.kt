package universal.appfactory.aeroindia2023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AgendaAdapter(private val mList: ArrayList<AgendaModel>) : RecyclerView.Adapter<AgendaAdapter.ViewHolder>() {

    // creating a variable for array list and context.
    private var agendaModelArrayList: ArrayList<AgendaModel>

    // method for filtering our recyclerview items.
    fun filterList(filterList: ArrayList<AgendaModel>) {
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
            .inflate(R.layout.agenda_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // setting data to our views of recycler view.
        val itemsViewModel = agendaModelArrayList[position]
        val date = itemsViewModel.getStartTime().subSequence(0,11)

        // sets the text to the textview from our itemHolder class
        holder.startTime.text = itemsViewModel.getStartTime().trim().drop(10)
        holder.endTime.text = itemsViewModel.getEndTime().trim().drop(10)
        holder.eventName.text = itemsViewModel.getEvent().trim()
        holder.date.text = date

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return agendaModelArrayList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var startTime: TextView = itemView.findViewById(R.id.start_time)
        var endTime: TextView = itemView.findViewById(R.id.end_time)
        var eventName: TextView = itemView.findViewById(R.id.event_name)
        var date: TextView = itemView.findViewById(R.id.date)

    }

    // creating a constructor for our variables.
    init {
        this.agendaModelArrayList = mList
    }

}