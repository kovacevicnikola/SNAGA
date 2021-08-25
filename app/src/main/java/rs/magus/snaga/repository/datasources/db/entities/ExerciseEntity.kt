package rs.magus.snaga.repository.datasources.db.entities

import androidx.room.Entity
import rs.magus.snaga.pojo.constants.TableNames
import rs.magus.snaga.repository.datasources.db.entities.baseEntities.BaseEntity

@Entity(tableName = TableNames.EXERCISES)
data class ExerciseEntity(
    val name: String,
    val description: String

) : BaseEntity() {
    override fun toString(): String {
        return name
    }
}