package universal.appfactory.aeroindia2023.faqs

import com.google.gson.annotations.SerializedName
import universal.appfactory.aeroindia2023.speakers.SpeakerModel

data class FaqsResponse (
    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<FAQsModel>
)