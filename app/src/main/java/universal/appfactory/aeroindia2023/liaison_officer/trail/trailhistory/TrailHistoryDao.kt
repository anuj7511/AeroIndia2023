package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhistory

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailModel

@Dao
interface TrailHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(data : List<TrailHistoryModel?>?)

    @Query("SELECT * FROM trailHistory")
    fun getTrailHistory() : LiveData<List<TrailHistoryModel>>

    @Query("DELETE FROM trailHistory")
    suspend fun deleteAll()
}