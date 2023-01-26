package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class ResolvedRemarkError(

    @SerializedName("complaint_id")
    var complaint_id: Array<String> = arrayOf("null msg"),
    @SerializedName("resolved_by")
    var resolved_by: Array<String> = arrayOf("null msg"),
    @SerializedName("resolved_remarks")
    var resolved_remarks: Array<String> = arrayOf("null msg")
)
