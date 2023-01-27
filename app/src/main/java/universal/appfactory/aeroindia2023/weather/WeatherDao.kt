package universal.appfactory.aeroindia2023.weather

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(speakers: List<TemperatureModel?>?)

    @Query("SELECT * FROM weather")
    fun getWeather(): LiveData<List<TemperatureModel>>

    @Query("DELETE FROM weather")
    suspend fun deleteAll()

}