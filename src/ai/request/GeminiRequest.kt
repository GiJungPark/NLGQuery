package ai.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class GeminiRequest(
    @JsonProperty("system_instruction") val systemInstruction: SystemInstruction,
    @JsonProperty("contents") val contents: Contents
) {
    fun toJson(): String {
        val objectMapper = jacksonObjectMapper()
        return objectMapper.writeValueAsString(this)
    }
}
