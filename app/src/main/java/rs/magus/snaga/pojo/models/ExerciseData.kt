package rs.magus.snaga.pojo.models

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity

data class ExerciseData(
    val exercise: MutableLiveData<ExerciseEntity> = MutableLiveData(),
    val sets: ObservableInt = ObservableInt(),
    val reps: ObservableInt = ObservableInt()

)
