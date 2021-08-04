package rs.magus.snaga.pojo.models

data class ExerciseData(
    val id: Int,
    val name: String,
    val group: Int,
    val defaultWeight: Double,
    val defaultReps: Int,
    val defaultSets: Int


) {
    override fun toString(): String {
        return name
    }
}
