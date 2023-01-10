package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhistory

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailDao
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailDatabase
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailModel


@Database(
    entities = [TrailHistoryModel::class],
    version = 1,
    exportSchema = true
)
abstract class TrailHistoryDatabase : RoomDatabase() {
    abstract fun trailHistoryDao() : TrailHistoryDao

    companion object {

        @Volatile
        private var INSTANCE: TrailHistoryDatabase? = null

        fun getDatabase(context: Context): TrailHistoryDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): TrailHistoryDatabase? {
            return Room.databaseBuilder(
                context.applicationContext,
                TrailHistoryDatabase::class.java,
                "Trail_history_database"
            ).build()

        }
    }
}