package rs.magus.snaga.pojo.models

import androidx.room.Embedded
import androidx.room.Ignore

data class ExerciseData(
    val id: Int,
    val name: String,
    val group: Int,
    val defaultWeight: Double,
    val defaultReps: Int,
    val defaultSets: Int,
    @Ignore val repsSetsAndWeight: List<RepsSetsAndWeight> //todo

) {
    override fun toString(): String {
        return name
    }
}
