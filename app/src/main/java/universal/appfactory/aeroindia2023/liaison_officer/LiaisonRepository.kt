package universal.appfactory.aeroindia2023.liaison_officer

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import universal.appfactory.aeroindia2023.ApiClient
import universal.appfactory.aeroindia2023.ApiInterface
import universal.appfactory.aeroindia2023.delegate.DelegateDatabase
import universal.appfactory.aeroindia2023.delegate.DelegateModel
import universal.appfactory.aeroindia2023.delegate.DelegateDao

class LiaisonRepository (application: Application){
    private var liaisonDao : LiaisonDao
    private var liaison : LiveData<List<LiaisonModel>>

    init {
        liaisonDao = application.let { LiaisonDatabase.getDatabase(it).liaisonDao() }
        liaison = liaisonDao.getLiaisonOfficers()
        Log.d(ContentValues.TAG, "New instance created")
    }

    fun getAllLiaisonOfficers(): LiveData<List<LiaisonModel>> {
        return liaisonDao.getLiaisonOfficers()
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun loadAllLiaisonOfficers(reload: Boolean,id : Int = 1) {
        if (reload) {
            liaisonDao.deleteAll()
            val liaisonApi = ApiClient.getInstance().create(ApiInterface::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                val value = liaisonApi.getLiaisonOfficers("Bearer 61b25a411a2dad66bb7b6ff145db3c2f",id)
                    ?.awaitResponse()
                val data = value?.body()?.data as List<LiaisonModel>
                Log.d("Response :", data.toString())
                liaisonDao.insertAll(data)
            }
        }
    }
}