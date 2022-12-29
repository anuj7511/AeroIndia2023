package universal.appfactory.aeroindia2023.faqs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FaqDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(faqs : List<FAQsModel?>?)

    @Query("SELECT * FROM faqs")
    fun getfaqs() : LiveData<List<FAQsModel>>

    @Query("DELETE FROM faqs")
    suspend fun deleteAll()
}