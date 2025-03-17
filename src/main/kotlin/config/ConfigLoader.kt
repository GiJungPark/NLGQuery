package config

import java.util.*

object ConfigLoader {
    private val properties: Properties = Properties()

    val apiKey: String
        get() = properties.getProperty("api.key") ?: throw IllegalStateException("API_KEY is missing")

    val databaseUrl: String
        get() = properties.getProperty("database.url") ?: throw IllegalStateException("DATABASE_URL is missing")

    val databaseUser: String
        get() = properties.getProperty("database.user") ?: throw IllegalStateException("DB_USERNAME is missing")

    val databasePassword: String
        get() = properties.getProperty("database.password") ?: throw IllegalStateException("DB_PASSWORD is missing")

    val databaseSchema: String
        get() = databaseUrl.split("/").last().split("?").first()

    init {
        val inputStream = this::class.java.classLoader.getResourceAsStream("config.properties")
        inputStream?.use { properties.load(it) }
    }
 }