package ai.request

import com.fasterxml.jackson.annotation.JsonProperty

data class Contents(
    @JsonProperty("parts") val parts: ContentsParts
)