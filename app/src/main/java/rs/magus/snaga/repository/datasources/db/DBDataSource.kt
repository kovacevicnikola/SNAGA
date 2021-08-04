package rs.magus.snaga.repository.datasources.db

import android.content.Context
import rs.magus.snaga.pojo.models.ExerciseData
import rs.magus.snaga.repository.datasources.db.entities.ExerciseLogEntity

class DBDataSource(
    context: Context
) {
    val db: AppDatabase = AppDatabase.getDatabase(context)

    suspend fun getExercises(): List<ExerciseData> {
        return db.exerciseDao().getAll()
    }

    suspend fun logExercise(entity: ExerciseLogEntity) {
        return db.exerciseLogDao().insert(entity)
    }
}