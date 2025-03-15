package db

import com.fasterxml.jackson.databind.ObjectMapper
import java.sql.ResultSet

object QueryExecutor {
    private val objectMapper = ObjectMapper()

    fun query(sql: String): String {
        val resultList = executeQuery(sql)
        return objectMapper.writeValueAsString(resultList)
    }

    private fun executeQuery(sql: String): List<Map<String, Any>> {
        val connection = DatabaseConnector.getConnection()
        connection.use { conn ->
            val ps = conn.prepareStatement(sql)
            ps.use { pstmt ->
                val resultSet = pstmt.executeQuery()
                resultSet.use { rs ->
                    return parseResultSet(rs)
                }
            }
        }
    }

    private fun parseResultSet(resultSet: ResultSet): List<Map<String, Any>> {
        val metaData = resultSet.metaData
        val columnCount = metaData.columnCount
        val resultList = mutableListOf<Map<String, Any>>()

        while (resultSet.next()) {
            val row = mutableMapOf<String, Any>()
            for (i in 1..columnCount) {
                val columnName = metaData.getColumnName(i)
                val value = resultSet.getObject(i)
                row[columnName] = value
            }
            resultList.add(row)
        }
        return resultList
    }
}