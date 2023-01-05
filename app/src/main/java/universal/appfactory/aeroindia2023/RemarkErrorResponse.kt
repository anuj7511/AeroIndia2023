package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class RemarkErrorResponse(
    @SerializedName("user_id")
    var user_id: Array<String> = arrayOf("null msg"),
    @SerializedName("washroom_id")
    var washroom_id: Array<String> = arrayOf("null msg"),
    @SerializedName("remarks")
    var remarks: Array<String> = arrayOf("null msg")

)
