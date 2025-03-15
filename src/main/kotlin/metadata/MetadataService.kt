package metadata

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import db.QueryExecutor

class MetadataService {
    private val objectMapper = ObjectMapper()

    fun getMetadata(schema: String): List<TableMetadata> {
        val metadataCache = TableMetadataCache.cache

        if (metadataCache.isEmpty()) {
            val metadata = loadMetadata(schema)
            TableMetadataCache.loadMetadata(metadata)

            return metadata
        }

        return metadataCache
    }

    private fun loadMetadata(schema: String): List<TableMetadata> {
        val sql = "SELECT\n" +
                "    TABLE_SCHEMA AS database_name,\n" +
                "    TABLE_NAME AS table_name,\n" +
                "    COLUMN_NAME AS column_name,\n" +
                "    DATA_TYPE AS column_type,\n" +
                "    IS_NULLABLE AS is_nullable,\n" +
                "    COLUMN_DEFAULT AS default_value\n" +
                "  FROM\n" +
                "    INFORMATION_SCHEMA.COLUMNS\n" +
                "  WHERE\n" +
                "    TABLE_SCHEMA = '$schema'"
        val result = QueryExecutor.query(sql)

        return objectMapper.readValue(result)
    }
}