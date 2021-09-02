package rs.magus.snaga.pojo.models

import androidx.lifecycle.MutableLiveData
import rs.magus.snaga.repository.datasources.db.entities.ExerciseDayExerciseEntity

data class NewExerciseDayData(
    val name: MutableLiveData<String>,
    val exercises: List<ExerciseDayExerciseEntity>,
    val displayExercises: MutableList<ExerciseData>
)