package universal.appfactory.aeroindia2023.Notifications

import com.google.gson.annotations.SerializedName

data class NotificationResponseModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("recipients")
    val recipients: Int,
    @SerializedName("external_id")
    val external_id: Boolean
    )