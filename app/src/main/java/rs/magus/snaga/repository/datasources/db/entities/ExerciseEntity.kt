package rs.magus.snaga.repository.datasources.db.entities

import androidx.room.Entity
import rs.magus.snaga.pojo.constants.TableNames

@Entity(tableName = TableNames.EXERCISES)
data class ExerciseEntity(
    val name: String,
    val group: Int
) : BaseEntity() {
    override fun toString(): String {
        return name
    }
}