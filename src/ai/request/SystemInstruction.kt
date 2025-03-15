package ai.request

import com.fasterxml.jackson.annotation.JsonProperty

data class SystemInstruction(
    @JsonProperty("parts") val parts: InstructionParts
)