package rs.magus.snaga.repository.datasources.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import rs.magus.snaga.repository.datasources.db.dao.ExerciseDao
import rs.magus.snaga.repository.datasources.db.dao.ExerciseLogDao
import rs.magus.snaga.repository.datasources.db.entities.ExerciseDayEntity
import rs.magus.snaga.repository.datasources.db.entities.ExerciseDayExerciseEntity
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity
import rs.magus.snaga.repository.datasources.db.entities.ExerciseLogEntity

@Database(
    entities = [
        ExerciseEntity::class,
        ExerciseLogEntity::class,
        ExerciseDayEntity::class,
        ExerciseDayExerciseEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): ExerciseLogDao
    abstract fun exerciseDao(): ExerciseDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "main.sqlite3"
            ).addCallback(CALLBACK).build()
            INSTANCE = instance
            instance
        }

        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                db.execSQL(
                    "INSERT INTO `Exercises` ('name', 'group') VALUES ('Deadlift', 'Back'), ('Lat pulldown', 'Back')," +
                            "('Rows', 'Back') , ('Bench Press', 'Chest'); "
                )
            }
        }
    }
}