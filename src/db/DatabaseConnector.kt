package db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection

object DatabaseConnector {
    private var dataSource: HikariDataSource? = null

    fun initializeConnectionPool(url: String, user: String, pwd: String) {
        if (dataSource == null) {
            val config = HikariConfig().apply {
                jdbcUrl = url
                username = user
                password = pwd
                driverClassName = MY_SQL_DRIVER
                maximumPoolSize = 10
            }
            dataSource = HikariDataSource(config)
        }
    }

    fun getConnection(): Connection {
        return dataSource?.connection ?: throw ConnectorException(message = NOT_INITIALIZED_CONNECTION_POOL)
    }

    fun closeConnectionPool() {
        dataSource?.close()
    }

    private const val MY_SQL_DRIVER = "com.mysql.cj.jdbc.Driver"
    private const val NOT_INITIALIZED_CONNECTION_POOL = "Connection pool is not initialized."
}
