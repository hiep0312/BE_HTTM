package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import app.model.Algorithm;

public class AlgorithmDao {
    private static String GET_ALL_ALGORITHM = "SELECT * FROM algorithm";

    public ResponseEntity<?> getAllAlgorithm() {
        List<Algorithm> list = new ArrayList<>();

        try (Connection conn = MySql.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(GET_ALL_ALGORITHM);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(
                    new Algorithm(
                        rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getString("description"), 
                        rs.getString("task")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(list);
    }
}
