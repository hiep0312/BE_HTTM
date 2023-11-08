package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import app.model.Model;


public class ModelDao {

    private static final String GET_MODEL_BY_TASK = "SELECT * FROM model WHERE task = ?";
    private static final String GET_MODEL_BY_ID = "SELECT * FROM model WHERE id = ?";
    private static final String ADD_MODEL = "INSERT INTO model(name, path, mos, datasetId, algorithmId, task) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_MODEL = "DELETE FROM model WHERE id = ?";

    public List<Model> getModelsByTask(String task) {
        List<Model> models = new ArrayList<>();

        try (Connection conn = MySql.getConnection()){
            PreparedStatement ps = conn.prepareStatement(GET_MODEL_BY_TASK);
            ps.setString(1, task);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                models.add(new Model(
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getString("path"), 
                    rs.getTimestamp("date"), 
                    rs.getFloat("mos"), 
                    rs.getInt("datasetId"), 
                    rs.getInt("algorithmId"), 
                    rs.getString("task")));
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return models;
    }

    public String getPath(int id) {
        try (Connection conn = MySql.getConnection()) {
            
            PreparedStatement ps = conn.prepareStatement(GET_MODEL_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getString("path");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ResponseEntity<?> addModel(Model model) {
        try (Connection conn = MySql.getConnection()){
            
            PreparedStatement ps = conn.prepareStatement(ADD_MODEL);
            ps.setString(1, model.getName());
            ps.setString(2, model.getPath());
            ps.setFloat(3, model.getMos());
            ps.setInt(4, model.getDatasetId());
            ps.setInt(5, model.getAlgorithmId());
            ps.setString(6, model.getTask());
            ps.executeUpdate();

            ps.close();
            conn.close();

            return ResponseEntity.ok().body("Add model successfully: " + model);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Add model failed");
        }
    }


    public ResponseEntity<?> deleteModel(int id) {
        try (Connection conn = MySql.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(DELETE_MODEL);

            ps.setInt(1, id);

            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return ResponseEntity.ok().body("Delete model successfully: " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
