package universal.appfactory.aeroindia2023.faqs.exhibitors

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import universal.appfactory.aeroindia2023.faqs.FAQsModel
import universal.appfactory.aeroindia2023.faqs.event.EventsModel

@Dao
interface ExhibitorsFaqDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(faqs : List<ExhibitorsFaqModel?>?)

    @Query("SELECT * FROM exhibitorsfaqs")
    fun getExhibitorsFaqs() : LiveData<List<ExhibitorsFaqModel>>

    @Query("DELETE FROM exhibitorsfaqs")
    suspend fun deleteAll()
}