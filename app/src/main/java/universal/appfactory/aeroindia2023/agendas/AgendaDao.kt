package universal.appfactory.aeroindia2023.agendas

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AgendaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(agendas: List<AgendaModel?>?)

    @Query("SELECT * FROM agendas")
    fun getAgendas(): LiveData<List<AgendaModel>>

    @Query("DELETE FROM agendas")
    suspend fun deleteAll()

}