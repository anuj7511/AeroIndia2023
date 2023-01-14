package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhistory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "trailHistory")
class TrailHistoryModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private var id: Int,

    @ColumnInfo(name ="liaison_officer_id")
    @SerializedName("liaison_officer_id")
    private var liaison_officer_id:Int,

    @ColumnInfo(name ="delegate_id")
    @SerializedName("delegate_id")
    private var delegate_id:Int,

    @ColumnInfo(name ="trail_status")
    @SerializedName("trail_status")
    private var trail_status:String,

    @ColumnInfo(name = "remarks")
    @SerializedName("remarks")
    private var remarks: String,

    @ColumnInfo(name ="entered_by")
    @SerializedName("entered_by")
    private var entered_by:Int,

    @ColumnInfo(name ="createdAt")
    @SerializedName("createdAt")
    private var createdAt:String,

    @ColumnInfo(name ="updatedAt")
    @SerializedName("updatedAt")
    private var updatedAt:String,

    @ColumnInfo(name ="remark_user")
    @SerializedName("remark_user")
    private var remark_user:String,

    @ColumnInfo(name ="delegation_name")
    @SerializedName("delegation_name")
    private var delegation_name:String,

    @ColumnInfo(name ="liaison_officer_firstname")
    @SerializedName("liaison_officer_firstname")
    private var liaison_officer_firstname:String,

    @ColumnInfo(name ="liaison_officer_lastname")
    @SerializedName("liaison_officer_lastname")
    private var liaison_officer_lastname:String,

){
    fun getId(): Int{
        return id
    }

    fun getLiaison_officer_id(): Int{
        return liaison_officer_id
    }

    fun getDelegate_id(): Int{
        return delegate_id
    }

    fun getTrail_status(): String{
        return trail_status
    }

    fun getRemarks(): String{
        return remarks
    }

    fun getEntered_by(): Int{
        return entered_by
    }

    fun getCreatedAt(): String{
        return createdAt
    }

    fun getUpdatedAt(): String{
        return updatedAt
    }

    fun getRemark_user(): String{
        return remark_user
    }

    fun getDelegation_name(): String{
        return delegation_name
    }
    fun getLiaison_officer_firstname(): String{
        return liaison_officer_firstname
    }

    fun getLiaison_officer_lastname(): String{
        return liaison_officer_lastname
    }








}