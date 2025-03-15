package ai

import ai.request.*
import ai.response.GeminiResponse

class GeminiClient(
    private val apiKey: String,
    private val httpService: HttpService
) {
    fun generateSql(systemInstruction: String, contentsText: String): String {
        val requestBody = GeminiRequest(
            systemInstruction = SystemInstruction(parts = InstructionParts(text = systemInstruction)),
            contents = Contents(parts = ContentsParts(text = contentsText))
        )

        val body = requestBody.toJson()
        val queryParams = "key=$apiKey"
        val uri = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?$queryParams"

        val result = httpService.sendPostRequest(uri, body).body()
        return GeminiResponse(result).toSql()
    }
}