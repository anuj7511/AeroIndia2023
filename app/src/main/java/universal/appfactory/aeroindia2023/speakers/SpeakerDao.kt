package universal.appfactory.aeroindia2023.speakers

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SpeakerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(speakers: List<SpeakerModel?>?)

    @Query("SELECT * FROM speakers")
    fun getSpeakers(): LiveData<List<SpeakerModel>>

    @Query("DELETE FROM speakers")
    suspend fun deleteAll()

}