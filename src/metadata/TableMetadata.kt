package metadata

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class TableMetadata @JsonCreator constructor(
    @JsonProperty("TABLE_SCHEMA") val tableSchema: String,
    @JsonProperty("TABLE_NAME") val tableName: String,
    @JsonProperty("COLUMN_NAME") val columnName: String,
    @JsonProperty("DATA_TYPE") val columnType: String?,
    @JsonProperty("IS_NULLABLE") val isNullable: String,
    @JsonProperty("COLUMN_DEFAULT") val defaultValue: String?
) {
    override fun toString(): String {
        return "Table: $tableName, Column: $columnName, Data Type: $columnType, Nullable: $isNullable, Default Value : $defaultValue"
    }
}