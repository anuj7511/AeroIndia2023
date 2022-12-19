package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class ExhibitorResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<ExhibitorModel>
)