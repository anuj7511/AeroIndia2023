package universal.appfactory.aeroindia2023.liaison_officer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import universal.appfactory.aeroindia2023.delegate.DelegateModel

@Dao
interface LiaisonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(data : List<LiaisonModel?>?)

    @Query("SELECT * FROM liaison")
    fun getLiaisonOfficers() : LiveData<List<LiaisonModel>>

    @Query("DELETE FROM liaison")
    suspend fun deleteAll()
}