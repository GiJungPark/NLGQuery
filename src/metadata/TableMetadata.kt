package metadata

data class TableMetadata (
    val tableSchema: String,
    val tableName: String,
    val columnName: String,
    val columnType: String?,
    val isNullable: String,
    val defaultValue: String?
) {
    override fun toString(): String {
        return "Table: $tableName, Column: $columnName, Data Type: $columnType, Nullable: $isNullable, Default Value : $defaultValue"
    }
}