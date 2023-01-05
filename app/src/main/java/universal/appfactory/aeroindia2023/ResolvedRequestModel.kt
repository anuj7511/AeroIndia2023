package universal.appfactory.aeroindia2023

import android.widget.EditText

data class ResolvedRequestModel(
    val complaint_id:Int,
    val resolved_by:String,
    val resolved_remarks: String,
)