package universal.appfactory.aeroindia2023.speakers

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpeakerViewModel : ViewModel() {

    lateinit var allspeaker: LiveData<List<SpeakerModel>>
    private lateinit var speakerDao: SpeakerDao
    private lateinit var repository: SpeakerRepository

    fun init(app: Application) {
        if (::allspeaker.isInitialized) return
        speakerDao = SpeakerDatabase.getDatabase(app).speakerDao()
        repository = SpeakerRepository(app)
        allspeaker = repository.getAllSpeakers()
    }


    fun loadAllSpeakers(reload: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadAllSpeakers(reload)
        }
    }

}