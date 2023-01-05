package universal.appfactory.aeroindia2023.faqs.exhibitors

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import universal.appfactory.aeroindia2023.faqs.FAQsModel

interface ExhibitorsFaqDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(faqs : List<FAQsModel?>?)

    @Query("SELECT * FROM faqs")
    fun getExhibitorsFaqs() : LiveData<List<FAQsModel>>

    @Query("DELETE FROM faqs")
    suspend fun deleteAll()
}