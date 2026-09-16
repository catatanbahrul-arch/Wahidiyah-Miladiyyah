package com.wahidiyah.miladiyyah.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "region_rules")
data class RegionRuleEntity(
    @PrimaryKey val regionCode: String,
    val regionName: String,
    val offsetMinutes: Int = 0,
    val sourceVersion: String = "physical-calendar"
)
