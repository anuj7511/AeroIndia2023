package universal.appfactory.aeroindia2023.delegate

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DelegateDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(faqs : List<DelegateModel?>?)

    @Query("SELECT * FROM delegate")
    fun getDelegates() : LiveData<List<DelegateModel>>

    @Query("DELETE FROM delegate")
    suspend fun deleteAll()
}