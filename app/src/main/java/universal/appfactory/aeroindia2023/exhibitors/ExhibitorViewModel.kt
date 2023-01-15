package universal.appfactory.aeroindia2023.exhibitors

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExhibitorViewModel : ViewModel() {

    private lateinit var allexhibitor: LiveData<List<ExhibitorModel>>
    private lateinit var exhibitorDao: ExhibitorDao
    private lateinit var repository: ExhibitorRepository

    fun init(app: Application) {
        if (::allexhibitor.isInitialized) return
        exhibitorDao = ExhibitorDatabase.getDatabase(app).exhibitorDao()
        repository = ExhibitorRepository(app)
        allexhibitor = repository.getAllExhibitors()
    }


    fun loadAllExhibitors(reload: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadAllExhibitors(reload)
        }
    }

}