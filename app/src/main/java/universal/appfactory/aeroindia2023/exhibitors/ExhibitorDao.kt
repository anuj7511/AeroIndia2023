package universal.appfactory.aeroindia2023.exhibitors

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExhibitorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(exhibitors: List<ExhibitorModel?>?)

    @Query("SELECT * FROM exhibitors")
    fun getExhibitors(): LiveData<List<ExhibitorModel>>

    @Query("DELETE FROM exhibitors")
    suspend fun deleteAll()

}