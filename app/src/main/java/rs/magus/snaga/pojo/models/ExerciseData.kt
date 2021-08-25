package rs.magus.snaga.pojo.models

import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity

data class ExerciseData(
    val exercise: ExerciseEntity,
    val sets: Int,
    val reps: Int

) {
    // @Ignore val repsSetsAndWeight: List<RepsSetsAndWeight> = ArrayList()

    override fun toString(): String {
        return exercise.name
    }
}
