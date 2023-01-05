package universal.appfactory.aeroindia2023.faqs.event

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import universal.appfactory.aeroindia2023.faqs.FAQsModel

@Dao
interface EventsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(faqs : List<EventsModel?>?)

    @Query("SELECT * FROM eventsfaqs")
    fun getEventsFaqs() : LiveData<List<EventsModel>>

    @Query("DELETE FROM eventsfaqs")
    suspend fun deleteAll()
}