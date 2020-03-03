package imageswarehouse.controller;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetDataSource {
    private final String FILE_PROP = "src/main/resources/db.properties";
    private DriverManagerDataSource dataSource;

    public GetDataSource() {
        Properties properties = new Properties();
        dataSource = new DriverManagerDataSource();
        try {
            properties.load(new FileInputStream(FILE_PROP));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbUrl);
            dataSource.setDriverClassName(driverClassName);
        } catch (
                IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public DriverManagerDataSource getDataSource() {
        return dataSource;
    }
}
