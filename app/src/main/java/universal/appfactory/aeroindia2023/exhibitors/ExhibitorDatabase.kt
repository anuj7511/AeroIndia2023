package universal.appfactory.aeroindia2023.exhibitors

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ExhibitorModel::class],
    version = 1,
    exportSchema = true
)
abstract class ExhibitorDatabase : RoomDatabase() {

    abstract fun exhibitorDao(): ExhibitorDao

    companion object {

        @Volatile
        private var INSTANCE: ExhibitorDatabase? = null

        fun getDatabase(context: Context): ExhibitorDatabase {
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

        private fun buildDatabase(context: Context): ExhibitorDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ExhibitorDatabase::class.java,
                "exhibitors_database"
            )
                .build()
        }
    }
}