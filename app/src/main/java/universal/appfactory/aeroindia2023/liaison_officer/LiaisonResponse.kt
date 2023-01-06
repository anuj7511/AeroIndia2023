package universal.appfactory.aeroindia2023.liaison_officer

import com.google.gson.annotations.SerializedName
import universal.appfactory.aeroindia2023.delegate.DelegateModel

class LiaisonResponse (
    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<LiaisonModel>
)