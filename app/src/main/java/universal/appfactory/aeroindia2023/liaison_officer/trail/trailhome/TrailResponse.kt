package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome

import com.google.gson.annotations.SerializedName
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailModel

class TrailResponse (
    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<TrailModel>
)