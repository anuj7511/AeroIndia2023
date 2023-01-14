package universal.appfactory.aeroindia2023

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserHistoryAdapter(private val UList: ArrayList<UserHistoryModel> ) : RecyclerView.Adapter<UserHistoryAdapter.ViewHolder>() {

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

        holder.manager.text= itemsViewModel.getmanager().trim()
        var x:Int=itemsViewModel.getStatus()
        if(x>1){
         holder.linearLayout3.setVisibility(View.VISIBLE)
         holder.resolvedRemarks.setVisibility(View.VISIBLE)
         holder.rRemarks.setVisibility(View.VISIBLE)
         holder.remarks.setVisibility(View.GONE)
         holder.Remark.setVisibility(View.GONE)

        val DT:String= itemsViewModel.getupdateDate().trim()
        val date:CharSequence=DT.subSequence(0,11)
        val time:String=DT.substring(11)
        holder.Updateddate.text=date
        holder.Updatedtime.text=time
            holder.resolvedRemarks.text = itemsViewModel.getresolvedremarks().trim()
        }

        val CDT:String= itemsViewModel.getcreateDate().trim()
        val Cdate:CharSequence=CDT.subSequence(0,11)
        val Ctime:String=CDT.substring(11)
        holder.Createddate.text=Cdate
        holder.Createdtime.text=Ctime
        holder.remarks.text=itemsViewModel.getremarks().trim()




    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return UList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var manager:TextView = itemView.findViewById(R.id.Mgr)
        var Createddate:TextView = itemView.findViewById(R.id.Cdate)
        var Createdtime:TextView = itemView.findViewById(R.id.Ctime)
        var Updateddate:TextView = itemView.findViewById(R.id.Udate)
        var Updatedtime: TextView = itemView.findViewById(R.id.Utime)
        var resolvedRemarks: TextView = itemView.findViewById(R.id.ResolvedRemarks)
        var linearLayout3: LinearLayout=itemView.findViewById(R.id.linearLayout3)
        var rRemarks:TextView=itemView.findViewById(R.id.rRemarks)
        var remarks:TextView=itemView.findViewById(R.id.feedback)
        var Remark:TextView=itemView.findViewById(R.id.Remarks)

    }
}