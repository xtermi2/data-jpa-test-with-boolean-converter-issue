package com.example.data_jpa_test_with_boolean_converter;

import com.zaxxer.hikari.util.DriverDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class H2CheckConstraintWithPlainJDBCTest {

    @Test
    void plain_JDBC_test() throws Exception {
        DataSource dataSource = new DriverDataSource(
                "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
                "org.h2.Driver",
                new Properties(),
                "sa",
                ""
        );

        String createTable = """
                create table my_entity (
                    paused char(1) check ((paused in ('N','Y'))),
                    version integer,
                    id bigint not null,
                    name varchar(120),
                    primary key (id)
                )""";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTable);
        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "insert into my_entity(name, paused, version, id) values(?, ?, ?, ?)")) {
            ps.setString(1, "Test Entity");
            ps.setString(2, "Y");
            ps.setInt(3, 0);
            ps.setLong(4, 1);
            ps.executeUpdate();
        }
    }
}
