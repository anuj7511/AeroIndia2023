package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class UserHistoryModel(
    @SerializedName("complaint_id")
    private var Cid:Int,
    @SerializedName("washroom_id")
    private var Wid:Int,
    @SerializedName("washroom")
    private var washroom:String,
    @SerializedName("event_id")
    private var event_id:String,
    @SerializedName("date_time")
    private var createdate:String,
    @SerializedName("action_taken")
    private var verifiedStatus:Int,
    @SerializedName("complained_by")
    private var complained_by:String,
    @SerializedName("remarks")
    private var remarks:String,
    @SerializedName("manager_id")
    private var manager_id:Int,
    @SerializedName("manager")
    private var manager:String,
    @SerializedName("resolved_remarks")
    private var resolved_remarks:String,
    @SerializedName("updated_at")
    private var updatedate:String,


    ) {
    fun getStatus(): Int{
        return verifiedStatus
    }
    fun getremarks():String{
        return remarks
    }
    fun getresolvedremarks():String{
        return resolved_remarks
    }
    fun getmanager():String{
        return manager
    }
    fun getupdateDate(): String {
        return updatedate
    }

    fun getcreateDate(): String {
        return createdate
    }


}
