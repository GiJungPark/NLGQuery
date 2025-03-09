package db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DatabaseConnector {
    private var connection: Connection? = null

    fun getConnection(url: String, user: String, password: String): Connection {
        if (connection == null) {
            try {
                Class.forName(MY_SQL_DRIVER)
                connection = DriverManager.getConnection(url, user, password)
            } catch (exception: ClassNotFoundException) {
                throw ConnectorException(NOT_FOUND_MY_SQL_JDBC_DRIVER, exception)
            } catch (sqlException: SQLException) {
                throw ConnectorException(FAILED_CONNECT_DATABASE, sqlException)
            }
        }

        return connection!!
    }

    fun closeConnection() {
        try {
            connection?.close()
        } catch (exception: SQLException) {
        }
    }

    private const val MY_SQL_DRIVER = "com.mysql.cj.jdbc.Driver"
    private const val NOT_FOUND_MY_SQL_JDBC_DRIVER = "MySQL JDBC 드라이버를 찾을 수 없습니다."
    private const val FAILED_CONNECT_DATABASE = "데이터베이스 연결에 실패했습니다."
}
