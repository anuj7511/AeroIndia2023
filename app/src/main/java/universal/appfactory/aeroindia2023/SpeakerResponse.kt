package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class SpeakerResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<SpeakerModel>
)