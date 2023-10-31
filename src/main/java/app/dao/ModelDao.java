package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.model.Model;

public class ModelDao {

    private static final String GET_MODEL_BY_TASK = "SELECT * FROM model WHERE task = ?";
    private static final String GET_MODEL_BY_ID = "SELECT * FROM model WHERE id = ?";

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
    
}
