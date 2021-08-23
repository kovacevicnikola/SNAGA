package rs.magus.snaga.pojo.models

data class ExerciseData(
    val id: Int,
    val name: String,
    val group: Int,
    val defaultWeight: Double,
    val defaultReps: Int,
    val defaultSets: Int,

) {
    // @Ignore val repsSetsAndWeight: List<RepsSetsAndWeight> = ArrayList()

    override fun toString(): String {
        return name
    }
}
