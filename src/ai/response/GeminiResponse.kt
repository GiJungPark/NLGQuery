package ai.response

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class GeminiResponse(
    val result: String,
) {
    fun toSql(): String {
        val objectMapper = jacksonObjectMapper()

        val rootNode: JsonNode = objectMapper.readTree(result)
        val textNode = rootNode["candidates"]?.get(0)
            ?.get("content")?.get("parts")?.get(0)
            ?.get("text")?.asText()

        val sql = textNode?.let {
            it.replace(Regex("```sql\\s*|```"), "").trim()
        }

        return sql ?: throw IllegalStateException("쿼리 생성에 실패하였습니다.")
    }
}
