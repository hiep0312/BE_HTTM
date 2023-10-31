package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import app.model.Sample;

public class SampleDao {

    private static final String GET_SAMPLE = "SELECT * FROM sample LIMIT ? OFFSET ?";
    private static final String ADD_SAMPLE = "INSERT INTO sample (name, audioId, transcriptId) VALUES (?, ?, ?)";
    private static final String DELETE_SAMPLE = "DELETE FROM sample WHERE id = ?";
    private static final String UPDATE_SAMPLE = "UPDATE sample SET name = ?, audioId = ?, transcriptId = ?, lastupdate = CURRENT_TIMESTAMP WHERE id = ?";
    private static final String GET_SAMPLE_BY_NAME = "SELECT * FROM sample WHERE name = ?";
    

    public List<Sample> getSamples(int start_idx, int cnt) {
        List<Sample> samples = new ArrayList<>();

        try (Connection conn = MySql.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) as total FROM sample");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int total = rs.getInt("total");

            if (start_idx >= total) {
                System.out.println("No sample");
                return samples;
            }

            int fixed_cnt = Math.min(cnt, total - start_idx);

            ps = conn.prepareStatement(GET_SAMPLE);
            ps.setInt(1, fixed_cnt);
            ps.setInt(2, start_idx);
            rs = ps.executeQuery();

            while (rs.next()) {
                samples.add(new Sample(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("audioId"),
                    rs.getInt("transcriptId"),
                    rs.getTimestamp("date"),
                    rs.getTimestamp("lastupdate")));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return samples;
    }

    public ResponseEntity<?> addSample(Sample sample) {
        try (Connection conn = MySql.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(ADD_SAMPLE);

            ps.setString(1, sample.getName());
            ps.setInt(2, sample.getAudioId());
            ps.setInt(3, sample.getTranscriptId());

            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return ResponseEntity.ok().body("Add sample successfully: " + sample);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("add sample failed");
        }
    }

    public ResponseEntity<?> deleteSample(int id) {
        try (Connection conn = MySql.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(DELETE_SAMPLE);

            ps.setInt(1, id);

            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return ResponseEntity.ok().body("Delete sample successfully: " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    public ResponseEntity<?> editSample(int id, String name, int audioId, int transcriptId) {
        try (Connection conn = MySql.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_SAMPLE)) {

            ps.setString(1, name);
            ps.setInt(2, audioId);
            ps.setInt(3, transcriptId);
            ps.setInt(4, id);

            ps.executeUpdate();

            ps.close();
            conn.close();
            return ResponseEntity.ok().body("Update sample successfully: " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    public ResponseEntity<?> getSampleByName(String name) {
        try (Connection conn = MySql.getConnection()){
            PreparedStatement ps = conn.prepareStatement(GET_SAMPLE_BY_NAME);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            rs.next();
            Sample sample = new Sample(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("audioId"),
                    rs.getInt("transcriptId"),
                    rs.getTimestamp("date"),
                    rs.getTimestamp("lastupdate"));
            return ResponseEntity.ok().body(sample);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("No sample with name: " + name);
        }
    }
}
