package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhistory

import com.google.gson.annotations.SerializedName
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailModel

class TrailHistoryResponse (
    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<TrailHistoryModel>
)