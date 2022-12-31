package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class ZonalManagerModel(
    @SerializedName("complaint_id")
    private var complaint_id:Int,
    @SerializedName("manager")
    private var manager:String,
    @SerializedName("event_id")
    private var event_id:String,
    @SerializedName("remarks")
    private var remarks:String,
    @SerializedName("date_time")
    private var date_time:String,
    @SerializedName("action_taken")
    private var action_taken:Int,
    @SerializedName("complained_by")
    private var complained_user:String,
    @SerializedName("washroom")
    private var washroom_id:String

    ) {
    fun getName(): String {
        return complained_user
    }
    fun getwashId():String
    {
        return washroom_id
    }
    fun getremarks(): String {
        return remarks
    }

    fun getdate_time(): String {
        return date_time
    }

    fun getstatus(): Int {
        return action_taken
    }
}
