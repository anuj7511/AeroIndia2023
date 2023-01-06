package universal.appfactory.aeroindia2023.liaison_officer.trail

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import universal.appfactory.aeroindia2023.liaison_officer.LiaisonModel

@Dao
interface TrailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(data : List<TrailModel?>?)

    @Query("SELECT * FROM trail")
    fun getTrail() : LiveData<List<TrailModel>>

    @Query("DELETE FROM trail")
    suspend fun deleteAll()
}