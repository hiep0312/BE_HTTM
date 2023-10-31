package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import app.model.Dataset;

public class DatasetDAO {
    private final static String GET_ALL_DATASET = "SELECT * FROM dataset";
    
    
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


    

    
}
