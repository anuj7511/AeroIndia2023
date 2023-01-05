package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("status")
    val status:String,
    @SerializedName("message")
    val message:String,
    @SerializedName("data")
    val data:String,
    @SerializedName("errors")
    val error:RemarkErrorResponse
)
