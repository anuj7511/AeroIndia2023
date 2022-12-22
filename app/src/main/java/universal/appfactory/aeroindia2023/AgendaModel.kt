package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

class AgendaModel (@SerializedName("id")
                   private var id: Int,
                   @SerializedName("session_name")
                   private var session_name: String,
                   @SerializedName("start_date_time")
                   private var start_date_time: String,
                   @SerializedName("end_date_time")
                   private var end_date_time: String,
                   @SerializedName("location_name")
                   private var location_name: String,
                   @SerializedName("categories")
                   private var categories: String,
                   @SerializedName("description")
                   private var description: String,
                   @SerializedName("speakers")
                   private var speakers: String,
                   @SerializedName("organiser")
                   private var organiser: String,
               ){

    fun getEvent(): String {
        return session_name
    }

    fun getStartTime(): String {
        return start_date_time
    }

    fun getEndTime(): String {
        return end_date_time
    }

    fun getLocationName(): String {
        return location_name
    }

    fun getCategories(): String {
        return categories
    }

    fun getDescription(): String {
        return description
    }

    fun getSpeakers(): String {
        return speakers
    }

}
