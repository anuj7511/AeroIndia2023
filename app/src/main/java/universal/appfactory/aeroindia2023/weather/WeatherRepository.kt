package universal.appfactory.aeroindia2023.weather

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import universal.appfactory.aeroindia2023.ApiClient
import universal.appfactory.aeroindia2023.ApiInterface


class WeatherRepository(application: Application) {

    private var weatherDao: WeatherDao
    private var weather: LiveData<List<TemperatureModel>>

    init {
        weatherDao = application.let { WeatherDatabase.getDatabase(it).weatherDao() }
        weather = weatherDao.getWeather()
        Log.d(TAG, "New instance created...")
    }


    fun getAllWeather(): LiveData<List<TemperatureModel>> {
        return weatherDao.getWeather()
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun loadAllWeather(reload: Boolean) {
        if(reload)
        {
            weatherDao.deleteAll()
            val weatherApi = ApiClient.getInstance2().create(ApiInterface::class.java)

            // launching a new coroutine
            GlobalScope.launch(Dispatchers.IO) {
                val value = weatherApi.getTemperature()?.awaitResponse()
                val data: List<TemperatureModel>? = value?.body()?.data as List<TemperatureModel>
                Log.d("Response: ", data.toString())
                weatherDao.insertAll(data)
            }
        }
    }

}