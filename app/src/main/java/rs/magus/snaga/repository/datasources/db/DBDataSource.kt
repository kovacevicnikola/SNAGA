package rs.magus.snaga.repository.datasources.db

import android.content.Context
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity

class DBDataSource(
    context: Context
) {
    val db: AppDatabase = AppDatabase.getDatabase(context)

    suspend fun getExercises(): List<ExerciseEntity> {
        return db.exerciseDao().getAll()
    }
}