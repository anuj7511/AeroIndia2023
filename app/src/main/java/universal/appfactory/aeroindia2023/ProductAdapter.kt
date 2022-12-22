package universal.appfactory.aeroindia2023

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class ProductAdapter(mList: ArrayList<ProductModel>, private val mContext: Context) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    // creating a variable for array list and context.
    private var productModelArrayList: ArrayList<ProductModel>

    // method for filtering our recyclerview items.
    fun filterList(filterList: ArrayList<ProductModel>) {
        // below line is to add our filtered
        // list in our course array list.
        productModelArrayList = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // setting data to our views of recycler view.
        val itemsViewModel = productModelArrayList[position]

        // sets the text to the textview from our itemHolder class
        holder.nameText.text = itemsViewModel.getProductTitle()

        holder.nextCard.setOnClickListener{
            val intent = Intent(mContext,SelectedProductActivity::class.java)
            intent.putExtra("Name",itemsViewModel.getProductTitle())
            intent.putExtra("Category",itemsViewModel.getFirstCategory())
            intent.putExtra("Exhibitor",itemsViewModel.getExhibitorName())
            intent.putExtra("Description",itemsViewModel.getDescription())
            intent.putExtra("Image",itemsViewModel.getProductImage())
            mContext.startActivity(intent)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return productModelArrayList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var nameText: TextView = itemView.findViewById(R.id.product_name)
        var numberText: TextView = itemView.findViewById(R.id.number)
        val nextCard: CardView = itemView.findViewById(R.id.product_card)

    }

    // creating a constructor for our variables.
    init {
        this.productModelArrayList = mList
    }

}