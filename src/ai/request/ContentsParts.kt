package ai.request

import com.fasterxml.jackson.annotation.JsonProperty

data class ContentsParts(
    @JsonProperty("text") val text: String
)