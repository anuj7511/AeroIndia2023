package universal.appfactory.aeroindia2023.exhibitors

import com.google.gson.annotations.SerializedName

data class ExhibitorResponse2(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<ExhibitorModel2>
)