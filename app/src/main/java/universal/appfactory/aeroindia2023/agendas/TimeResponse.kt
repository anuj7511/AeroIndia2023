package universal.appfactory.aeroindia2023.agendas

import com.google.gson.annotations.SerializedName

data class TimeResponse(@SerializedName("status")
                          val status: Int,
                            @SerializedName("message")
                          val message: String,
                            @SerializedName("data")
                          val data: List<Time>)

class Time (@SerializedName("start_date_time")
                  private var start_date_time: String,) {

    fun getStart_date_time(): String {
        return start_date_time
    }
}
