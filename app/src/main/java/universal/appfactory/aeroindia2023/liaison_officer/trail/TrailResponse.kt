package universal.appfactory.aeroindia2023.liaison_officer.trail

import com.google.gson.annotations.SerializedName
import universal.appfactory.aeroindia2023.delegate.DelegateModel

class TrailResponse (
    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<TrailModel>
)