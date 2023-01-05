package universal.appfactory.aeroindia2023

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ZonalManagerAdapter(private val ZList: ArrayList<ZonalManagerModel>,private val mContext: Context) : RecyclerView.Adapter<ZonalManagerAdapter.ViewHolder>() {

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
        holder.washId.text=itemsViewModel.getwashId().trim()
        holder.nameText.text = itemsViewModel.getName().trim()
        holder.remarks.text = itemsViewModel.getremarks().trim()
        var DT:String= itemsViewModel.getdate_time().trim()
        var date:CharSequence=DT.subSequence(0,11)
        var time:String=DT.substring(11)
        holder.date.text=date
        holder.time.text=time
        if(itemsViewModel.getstatus()==1)
            holder.status.text="Pending"
        else
            holder.status.text="Resolved"
        holder.status.setOnClickListener{
            val intent=Intent(mContext,ResolvedRemarksActivity::class.java)
            intent.putExtra("CompId",itemsViewModel.getComplaintId())
            intent.putExtra("Manager",itemsViewModel.getM())
            mContext.startActivity(intent)
        }


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return ZList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var washId:TextView=itemView.findViewById(R.id.BId)
        var nameText: TextView = itemView.findViewById(R.id.user)
        var remarks: TextView = itemView.findViewById(R.id.r)
        var date: TextView = itemView.findViewById(R.id.date)
        var time:TextView=itemView.findViewById(R.id.time)
        var status: TextView = itemView.findViewById(R.id.Rstatus)


    }
}