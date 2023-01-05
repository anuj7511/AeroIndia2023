package universal.appfactory.aeroindia2023.faqs.media

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import universal.appfactory.aeroindia2023.faqs.FAQsModel

@Dao
interface MediaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(faqs : List<MediaModel?>?)

    @Query("SELECT * FROM mediafaqs")
    fun getMediaFaqs() : LiveData<List<MediaModel>>

    @Query("DELETE FROM mediafaqs")
    suspend fun deleteAll()
}