package universal.appfactory.aeroindia2023.delegate

import com.google.gson.annotations.SerializedName

data class DelegateResponse(
    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<DelegateModel>
)
