package universal.appfactory.aeroindia2023.weather

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

data class WeatherResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val data: List<TemperatureModel>,
)

@Entity(tableName = "weather")
data class TemperatureModel(
    @PrimaryKey
    @ColumnInfo(name = "app_max_temp")
    @SerializedName("app_max_temp")
    private var app_max_temp: Double,
    @ColumnInfo(name = "app_min_temp")
    @SerializedName("app_min_temp")
    private var app_min_temp: Double,
    @ColumnInfo(name = "datetime")
    @SerializedName("datetime")
    private var datetime: String,
    @ColumnInfo(name = "precip")
    @SerializedName("precip")
    private var precip: Double,
    @ColumnInfo(name = "weather")
    @SerializedName("weather")
    private var weather: Weather,
) {

    fun getApp_max_temp(): Double {
        return app_max_temp
    }

    fun getApp_min_temp(): Double {
        return app_min_temp
    }

    fun getDatetime(): String {
        return datetime
    }

    fun getPrecip(): Double {
        return precip
    }

    fun getWeather(): Weather {
        return weather
    }
}

data class Weather(
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,
)

class WeatherConverters {
    @TypeConverter
    fun fromDescription(value: String?): Weather? {
        return value?.let { Weather(it) }
    }

    @TypeConverter
    fun weatherToDescription(weather: Weather?): String? {
        return weather?.description
    }
}