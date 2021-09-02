package rs.magus.snaga.pojo.models

import androidx.databinding.ObservableInt
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity

data class ExerciseData(
    var exercise: ExerciseEntity = ExerciseEntity("", ""),
    val sets: ObservableInt = ObservableInt(1),
    val reps: ObservableInt = ObservableInt(1)

)
