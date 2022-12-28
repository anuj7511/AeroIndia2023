package universal.appfactory.aeroindia2023.agendas

import com.google.gson.annotations.SerializedName

data class LocationResponse(@SerializedName("status")
                          val status: Int,
                            @SerializedName("message")
                          val message: String,
                            @SerializedName("data")
                          val data: List<Location>)

class Location (@SerializedName("location_name")
                  private var location_name: String,) {

    fun getLocation_name(): String {
        return location_name
    }
}
