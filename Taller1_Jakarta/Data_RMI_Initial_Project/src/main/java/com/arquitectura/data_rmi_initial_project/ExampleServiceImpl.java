package com.arquitectura.data_rmi_initial_project;

import jakarta.ejb.Stateless;
import jakarta.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ExampleServiceImpl implements ExampleService {

    @Resource(lookup = "java:/MySqlDS")
    private DataSource dataSource;

    @Override
    public String fetchData() {
        return "Data from remote EJB";
    }

    @Override
    public List<String> fetchDatabaseData() {
        List<String> results = new ArrayList<>();
        String sql = "SELECT * FROM sakila.actor";  // adjust SQL as needed
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(rs.getString("actor_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
