package metadata

object TableMetadataCache {
    private var _cache: List<TableMetadata> = emptyList()

    val cache: List<TableMetadata>
        get() = _cache

    fun loadMetadata(metadata: List<TableMetadata>) {
        _cache = metadata
    }

    fun refreshMetadata(metadata: List<TableMetadata>) {
        _cache = metadata
    }
}