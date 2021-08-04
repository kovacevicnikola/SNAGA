package rs.magus.snaga.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import rs.magus.snaga.pojo.models.ExerciseData
import rs.magus.snaga.repository.Repository
import rs.magus.snaga.repository.datasources.db.entities.ExerciseLogEntity

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    var selectedExercise: ExerciseData? = null
    var exercises: List<ExerciseData> = ArrayList()
    val repository: Repository = Repository(application)
    suspend fun insertExerciseLog(entity: ExerciseLogEntity) {
        repository.logExercise(entity)
    }

    suspend fun getExercisesFromDB(): List<ExerciseData> {
        exercises = repository.getExercises()
        return exercises
    }

}