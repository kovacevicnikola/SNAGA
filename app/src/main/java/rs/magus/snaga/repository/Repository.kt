package rs.magus.snaga.repository

import android.content.Context
import rs.magus.snaga.repository.datasources.db.DBDataSource
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity

data class Repository(val context: Context) {
    private val dbDataSource: DBDataSource = DBDataSource(context)

    suspend fun getExercises(): List<ExerciseEntity> {
        return dbDataSource.getExercises()
    }
}