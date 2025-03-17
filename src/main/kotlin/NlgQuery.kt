import ai.GeminiClient
import ai.HttpService
import config.ConfigLoader
import db.DatabaseConnector
import db.QueryExecutor
import metadata.MetadataService

class NlgQuery {
    fun run(command: String): String {
        val data = metadataService.getMetadata(schema).toString()
        val promptCommand = SYSTEM_INSTRUCTION_PREFIX + data + SYSTEM_INSTRUCTION_SUFFIX
        val prompt = GeminiClient(apiKey, HttpService())
        val sql = prompt.generateSql(systemInstruction = promptCommand, contentsText = command)

        return QueryExecutor.query(sql)
    }

    companion object {
        const val SYSTEM_INSTRUCTION_PREFIX = "It's Database Metadata. : "
        const val SYSTEM_INSTRUCTION_SUFFIX = "Output only the query without any formatting, JSON, or additional text."

        private val metadataService = MetadataService()

        init {
            DatabaseConnector.initializeConnectionPool(
                ConfigLoader.databaseUrl,
                ConfigLoader.databaseUser,
                ConfigLoader.databasePassword
            )
        }
        val schema = ConfigLoader.databaseSchema
        val apiKey = ConfigLoader.apiKey
    }
}