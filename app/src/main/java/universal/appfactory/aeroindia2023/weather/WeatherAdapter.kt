package universal.appfactory.aeroindia2023.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import universal.appfactory.aeroindia2023.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class WeatherAdapter(mList: ArrayList<TemperatureModel>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    // creating a variable for array list and context.
    private var weatherModelArrayList: ArrayList<TemperatureModel>

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // setting data to our views of recycler view.
        val itemsViewModel = weatherModelArrayList[position]

        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = inFormat.parse(itemsViewModel.getDatetime()) as Date
        val outFormat = SimpleDateFormat("EEE")
        val day: String = outFormat.format(date)
        val maxTemp = itemsViewModel.getApp_max_temp().toString() + "°"
        val minTemp = "/" + itemsViewModel.getApp_min_temp().toString() + "°"
        val precipitation = itemsViewModel.getPrecip().toString() + "%"

        // sets the text to the textview from our itemHolder class
        holder.dayText.text = day
        holder.dateText.text = itemsViewModel.getDatetime().substring(5)
        holder.maxTempText.text = maxTemp
        holder.minTempText.text = minTemp
        holder.precipitationText.text = precipitation
        holder.descriptionText.text = itemsViewModel.getWeather().description

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return weatherModelArrayList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        // creating variables for our views.
        var dayText: TextView = itemView.findViewById(R.id.day)
        var dateText: TextView = itemView.findViewById(R.id.date)
        var maxTempText: TextView = itemView.findViewById(R.id.maxTemp)
        var minTempText: TextView = itemView.findViewById(R.id.minTemp)
        var precipitationText: TextView = itemView.findViewById(R.id.precipitation)
        var descriptionText: TextView = itemView.findViewById(R.id.WeatherDescription)
    }

    // creating a constructor for our variables.
    init {
        this.weatherModelArrayList = mList
    }

}