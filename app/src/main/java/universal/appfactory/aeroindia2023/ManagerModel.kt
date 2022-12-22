package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class ManagerModel(
    @SerializedName("complaint_id")
    private var complaint_id:String,
    @SerializedName("manager_id")
    private var manager_id:String,
    @SerializedName("manager")
    private var manager:String,
    @SerializedName("event_id")
    private var event_id:Int,
    @SerializedName("remarks")
    private var remarks:String,
    @SerializedName("date_time")
    private var date_time:String,
    @SerializedName("action_taken")
    private var action_taken:String,
    @SerializedName("complained_user")
    private var complained_user:String,

)
{
    fun getName():String{
        return complained_user
    }
    fun getremarks():String{
        return remarks
    }
    fun getdate_time():String{
        return date_time
    }
    fun getstatus():String{
        return action_taken
    }
}