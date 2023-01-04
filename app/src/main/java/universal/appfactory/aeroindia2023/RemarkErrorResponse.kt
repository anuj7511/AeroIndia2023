package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class RemarkErrorResponse(
    @SerializedName("user_id")
    var user_id:String,
    @SerializedName("washroom_id")
    var washroom_id:String,
    @SerializedName("remarks")
    var remarks:String

)
