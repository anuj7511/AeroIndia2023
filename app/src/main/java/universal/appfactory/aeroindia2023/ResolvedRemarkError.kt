package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class ResolvedRemarkError(

    @SerializedName("complaint_id")
    var user_id: Array<String> = arrayOf("null msg"),
    @SerializedName("manager_id")
    var washroom_id: Array<String> = arrayOf("null msg"),
    @SerializedName("resolved_remarks")
    var remarks: Array<String> = arrayOf("null msg")
)
