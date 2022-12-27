package universal.appfactory.aeroindia2023.speakers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SpeakerModel::class],
    version = 1,
    exportSchema = true
)
abstract class SpeakerDatabase : RoomDatabase() {

    abstract fun speakerDao(): SpeakerDao

    companion object {

        @Volatile
        private var INSTANCE: SpeakerDatabase? = null

        fun getDatabase(context: Context): SpeakerDatabase {
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

        private fun buildDatabase(context: Context): SpeakerDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                SpeakerDatabase::class.java,
                "speakers_database"
            )
                .build()
        }
    }
}