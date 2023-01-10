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
    var id: String
)