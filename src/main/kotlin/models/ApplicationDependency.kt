package models

data class ApplicationDependency(
    val dependency: String,
    val version: String,
    val isRemovable: Boolean = true,
    var isSelected: Boolean = false
)