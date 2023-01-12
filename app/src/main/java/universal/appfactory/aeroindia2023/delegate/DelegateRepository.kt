package universal.appfactory.aeroindia2023.delegate

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.RoomOpenHelper.Delegate
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import universal.appfactory.aeroindia2023.ApiClient
import universal.appfactory.aeroindia2023.ApiInterface

class DelegateRepository(application: Application) {

    private var delegateDao : DelegateDao
    private var delegate : LiveData<List<DelegateModel>>

    init {
        delegateDao = application.let { DelegateDatabase.getDatabase(it).delegateDao() }
        delegate = delegateDao.getDelegates()
        Log.d(ContentValues.TAG, "New instance created")
    }

    fun getAllFaqs(): LiveData<List<DelegateModel>> {
        return delegateDao.getDelegates()
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun loadAllDelegates(reload: Boolean) {
        if (reload) {
            delegateDao.deleteAll()
            val delegateApi = ApiClient.getInstance().create(ApiInterface::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                val value = delegateApi.getDelegates("Bearer 61b25a411a2dad66bb7b6ff145db3c2f",1)
                    ?.awaitResponse()
                val data: List<DelegateModel>? = value?.body()?.data as List<DelegateModel>
                Log.d("DelegateResponse:", data.toString())
                delegateDao.insertAll(data)
            }
        }
    }
}