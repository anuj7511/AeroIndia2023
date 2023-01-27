package universal.appfactory.aeroindia2023.weather

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    lateinit var allweather: LiveData<List<TemperatureModel>>
    private lateinit var weatherDao: WeatherDao
    private lateinit var repository: WeatherRepository

    fun init(app: Application) {

        if (::allweather.isInitialized) return
        weatherDao = WeatherDatabase.getDatabase(app).weatherDao()
        repository = WeatherRepository(app)
        allweather = repository.getAllWeather()
    }


    fun loadAllWeather(reload: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadAllWeather(reload)
        }
    }

}