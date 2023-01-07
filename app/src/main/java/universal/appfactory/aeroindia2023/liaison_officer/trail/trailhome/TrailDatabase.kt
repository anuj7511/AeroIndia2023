package universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TrailModel::class],
    version = 1,
    exportSchema = true
)
abstract class TrailDatabase : RoomDatabase() {
    abstract fun trailDao() : TrailDao

    companion object {

        @Volatile
        private var INSTANCE: TrailDatabase? = null

        fun getDatabase(context: Context): TrailDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }

            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): TrailDatabase? {
            return Room.databaseBuilder(
                context.applicationContext,
                TrailDatabase::class.java,
                "Trail_database"
            ).build()

        }
    }
}