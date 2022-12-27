package universal.appfactory.aeroindia2023.agendas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [AgendaModel::class],
    version = 1,
    exportSchema = true
)
abstract class AgendaDatabase : RoomDatabase() {

    abstract fun agendaDao(): AgendaDao

    companion object {

        @Volatile
        private var INSTANCE: AgendaDatabase? = null

        fun getDatabase(context: Context): AgendaDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): AgendaDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AgendaDatabase::class.java,
                "agendas_database"
            )
                .build()
        }
    }
}