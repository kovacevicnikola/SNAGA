package rs.magus.snaga.repository.datasources.db.entities

import rs.magus.snaga.repository.datasources.db.entities.baseEntities.BaseEntity

data class ExerciseDayDateEntity(
    val exerciseDayID: Int,
    val dateTime: String
) : BaseEntity()