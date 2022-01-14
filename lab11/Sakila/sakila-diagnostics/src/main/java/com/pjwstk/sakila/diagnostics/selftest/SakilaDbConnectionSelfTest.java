package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SakilaDbConnectionSelfTest extends SelfTestBase {

    @Value("${spring.datasource.url}")
    private String dbConn;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public SakilaDbConnectionSelfTest() {
        name = "SakilaDBConnectionSelfTest";
        description = "Checks if establishing connection with sakila database is possible.";
    }

    @Override
    public SelftestResult execute() {
        List<String> errors = new ArrayList<>();
        SelftestResult selftestResult = new SelftestResult(name, description, false, errors);

        try (Connection connection = DriverManager.getConnection(dbConn, username, password);) {
            selftestResult.setPassed(true);
        }
        catch (SQLException e) {
            selftestResult.setPassed(false);
            errors.add(e.getMessage());
        }
        return selftestResult;
    }
}
