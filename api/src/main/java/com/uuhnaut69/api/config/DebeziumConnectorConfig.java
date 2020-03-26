package com.uuhnaut69.api.config;

import io.debezium.connector.mysql.MySqlConnectorConfig;
import io.debezium.embedded.EmbeddedEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author uuhnaut
 * @project demo
 */
@Configuration
public class DebeziumConnectorConfig {

    public static final String APP_NAME = "mysql-connector";
    public static final String PRODUCT_TABLE = "product";

    @Value("${offsetDir}")
    private String offsetDir;

    @Value("${historyDir}")
    private String historyDir;

    @Value("${dbHost}")
    private String dbHost;

    @Value("${dbUser}")
    private String dbUser;

    @Value("${dbPassword}")
    private String dbPassword;

    @Value("${dbName}")
    private String dbName;

    /**
     * Configuration database connector.
     *
     * @return Configuration.
     */
    @Bean
    public io.debezium.config.Configuration studentConnector() {
        return io.debezium.config.Configuration.create()
                .with(EmbeddedEngine.CONNECTOR_CLASS, "io.debezium.connector.mysql.MySqlConnector")
                .with(EmbeddedEngine.OFFSET_STORAGE, "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with(EmbeddedEngine.OFFSET_STORAGE_FILE_FILENAME, offsetDir)
                .with(EmbeddedEngine.OFFSET_FLUSH_INTERVAL_MS, 60000)
                .with(EmbeddedEngine.ENGINE_NAME, APP_NAME)
                .with(MySqlConnectorConfig.SERVER_NAME, APP_NAME)
                .with(MySqlConnectorConfig.HOSTNAME, dbHost)
                .with(MySqlConnectorConfig.PORT, 3306)
                .with(MySqlConnectorConfig.SERVER_ID, 223344)
                .with(MySqlConnectorConfig.USER, dbUser)
                .with(MySqlConnectorConfig.PASSWORD, dbPassword)
                .with("database.dbname", dbName)
                .with(MySqlConnectorConfig.DATABASE_HISTORY, "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", historyDir)
                .with("schemas.enable", false)
                .build();
    }
}
