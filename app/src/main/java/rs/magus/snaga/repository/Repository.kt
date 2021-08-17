package rs.magus.snaga.repository

import android.content.Context
import rs.magus.snaga.pojo.models.ExerciseData
import rs.magus.snaga.repository.datasources.db.DBDataSource
import rs.magus.snaga.repository.datasources.db.entities.ExerciseLogEntity

data class Repository(val context: Context) {
    private val dbDataSource: DBDataSource = DBDataSource(context)

    suspend fun getExercises(): List<ExerciseData> {
        return dbDataSource.getExercises()
    }

    suspend fun logExercise(entity: ExerciseLogEntity) {
        dbDataSource.logExercise(entity)
    }
    suspend fun logExercises(entities: List<ExerciseLogEntity>) {
        return dbDataSource.logExercises(entities)
    }
}