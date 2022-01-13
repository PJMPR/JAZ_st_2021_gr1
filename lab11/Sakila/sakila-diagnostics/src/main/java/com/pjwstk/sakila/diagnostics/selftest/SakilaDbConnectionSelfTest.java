package com.pjwstk.sakila.diagnostics.selftest;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SakilaDbConnectionSelfTest implements ISelfTest {

    @Value("${spring.datasource.url}")
    private String conn;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
    

    @Override
    public SelftestResult execute() {
        List<String> errors = new ArrayList<>();
        SelftestResult selftestResult = new SelftestResult(getName(), getDescription(), false, errors);

        try (Connection connection = DriverManager.getConnection(conn, username, password);) {
            selftestResult.setPassed(true);
        }
        catch (SQLException e) {
            selftestResult.setPassed(false);
            errors.add(e.getMessage());
        }
        return selftestResult;
    }

    @Override
    public String getName() {

        return "SakilaDBConnectionSelfTest";
    }

    @Override
    public String getDescription() {

        return "Checks sakila db connection";
    }
}
