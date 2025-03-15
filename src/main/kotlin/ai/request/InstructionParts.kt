package ai.request

import com.fasterxml.jackson.annotation.JsonProperty

data class InstructionParts(
    @JsonProperty("text") val text: String
)