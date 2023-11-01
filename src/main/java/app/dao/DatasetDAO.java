package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import app.model.Dataset;

public class DatasetDAO {
    private final static String GET_ALL_DATASET = "SELECT * FROM dataset";
    private final static String ADD_DATASET = "INSERT INTO dataset (name) VALUES (?)";
    
    
    public List<Dataset> getAllDataset() {
        List<Dataset> list = new ArrayList<>();

        try (Connection conn = MySql.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_ALL_DATASET)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Dataset(rs.getInt("id"), rs.getString("name")));
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public ResponseEntity<?> addDataset(Dataset dataset) {
        try (Connection conn = MySql.getConnection()){
            
            PreparedStatement ps = conn.prepareStatement(ADD_DATASET);
            ps.setString(1, dataset.getName());
            ps.executeUpdate();

            ps.close();
            conn.close();

            return ResponseEntity.ok().body("Add dastaset successfully: " + dataset.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Add dataset failed");
        }
    }


    

    
}
