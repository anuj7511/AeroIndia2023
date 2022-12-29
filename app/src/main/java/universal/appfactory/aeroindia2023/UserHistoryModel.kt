package universal.appfactory.aeroindia2023

import com.google.gson.annotations.SerializedName

data class UserHistoryModel(
    @SerializedName("id")
    private var id:Int,
    @SerializedName("name")
    private var name:String,
    @SerializedName("phone")
    private var phone:String,
    @SerializedName("email_id")
    private var email_id:String,
    @SerializedName("password")
    private var password:String,
    @SerializedName("verified_status")
    private var verifiedStatus:String,
    @SerializedName("updated_at")
    private var updatedate:String,
    @SerializedName("created_at")
    private var createdate:String,

    ) {
    fun getverifiedstatus(): String {
        return verifiedStatus
    }

    fun getupdateDate(): String {
        return updatedate
    }

    fun getcreateDate(): String {
        return createdate
    }


}
