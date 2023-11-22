package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import app.model.ObjectIndex;
import app.model.Transcript;


public class TranscriptDao {
    private static final String GET_COUNT_TRANSCRIPT = "SELECT COUNT(*) as total FROM transcript";
    private static final String GET_TRANSCRIPT = "SELECT * FROM transcript LIMIT ? OFFSET ?";
   // private static final String GET_TRANSCRIPT_NEW = "SELECT * FROM transcript Order by ? ? LIMIT ? OFFSET ?";
    private static final String ADD_TRANSCRIPT = "INSERT INTO transcript (name, content) VALUES (?, ?)";
    private static final String UPDATE_TRANSCRIPT = "UPDATE transcript SET content = ?, name = ?, lastupdate = CURRENT_TIMESTAMP WHERE id = ?";
    private static final String DELETE_TRANSCRIPT = "DELETE FROM transcript WHERE id = ?";
    private static final String GET_TRANSCRIPT_BY_NAME = "SELECT * FROM transcript WHERE name = ?";
    private static final String GET_TRANSCRIPT_BY_ID = "SELECT * FROM transcript WHERE id = ?";

    public ResponseEntity<?> getTranscript(ObjectIndex transcriptIndex) {
        List<Transcript> transcripts = new ArrayList<Transcript>();

        try (Connection conn = MySql.getConnection()) {


            PreparedStatement ps = conn.prepareStatement(GET_COUNT_TRANSCRIPT);

            ResultSet rs = ps.executeQuery();
            rs.next();
            int total = rs.getInt("total");
           
            if (transcriptIndex.getStart_idx() >= total) {
                System.out.println("No transcript");
                ResponseEntity.ok().body(transcripts);            }

            int fixed_cnt = Math.min(transcriptIndex.getCount(), total - transcriptIndex.getStart_idx());
           
            ps = conn.prepareStatement(getTranscriptQuery(transcriptIndex.getStart_idx(), fixed_cnt, transcriptIndex.getSortBy(), transcriptIndex.isAscend()));
            // ps.setString(1, sortBy);
            // ps.setString(2, ascend ? "asc" : "desc");
            // ps.setInt(3, fixed_cnt);
            // ps.setInt(4, startIdx);
            System.out.println(ps.toString());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                transcripts.add(new Transcript(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("content"),
                    rs.getTimestamp("date"),
                    rs.getTimestamp("lastupdate")
                ));
            } 
            ps.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ResponseEntity.ok().body(transcripts);
    }

    public List<Transcript> getTranscript(int startIdx, int cnt, String sortBy, boolean ascend) {
        List<Transcript> transcripts = new ArrayList<Transcript>();

        try (Connection conn = MySql.getConnection()) {


            PreparedStatement ps = conn.prepareStatement(GET_COUNT_TRANSCRIPT);

            ResultSet rs = ps.executeQuery();
            rs.next();
            int total = rs.getInt("total");
           
            if (startIdx >= total) {
                System.out.println("No transcript");
                return transcripts;
            }

            int fixed_cnt = Math.min(cnt, total - startIdx);
           
            ps = conn.prepareStatement(getTranscriptQuery(startIdx, fixed_cnt, sortBy, ascend));
            // ps.setString(1, sortBy);
            // ps.setString(2, ascend ? "asc" : "desc");
            // ps.setInt(3, fixed_cnt);
            // ps.setInt(4, startIdx);
            System.out.println(ps.toString());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                transcripts.add(new Transcript(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("content"),
                    rs.getTimestamp("date"),
                    rs.getTimestamp("lastupdate")
                ));
            } 
            ps.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return transcripts;
    }

    private String getTranscriptQuery(int startIdx, int cnt, String sortBy, boolean ascend) {
        return "SELECT * FROM transcript Order by " + sortBy + (ascend ? " asc" : " desc") + " LIMIT " + cnt + " OFFSET " + startIdx;
    }


    public ResponseEntity<?> addTranscript(Transcript transcript) {
        try (Connection conn = MySql.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(ADD_TRANSCRIPT);

            ps.setString(1, transcript.getName());
            ps.setString(2, transcript.getContent());

            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return ResponseEntity.ok().body("Add transcript successfully: " + transcript);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<String> editTranscript(int id, String name, String content) {
        try (Connection conn = MySql.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_TRANSCRIPT)) {

            ps.setString(1, content);
            ps.setString(2, name);
            ps.setInt(3, id);

            ps.executeUpdate();

            ps.close();
            conn.close();
            return ResponseEntity.ok().body("Update transcript successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Update transcript failed");
        }
    }

    public ResponseEntity<String> editTranscript(Transcript transcript) {
        try (Connection conn = MySql.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_TRANSCRIPT)) {

            ps.setString(1, transcript.getContent());
            ps.setString(2, transcript.getName());
            ps.setInt(3, transcript.getId());

            ps.executeUpdate();

            ps.close();
            conn.close();
            return ResponseEntity.ok().body("Update transcript successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Update transcript failed");
        }
    }


    public ResponseEntity<String> deleteTranscript(int id) {
        try (Connection conn = MySql.getConnection()) {

            PreparedStatement ps = conn.prepareStatement("DELETE FROM sample WHERE transcriptId = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            ps = conn.prepareStatement(DELETE_TRANSCRIPT);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            conn.close();

            return ResponseEntity.ok().body("Delete transcript successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Delete transcript failed");
        }
    }

    public ResponseEntity<?> getTranscriptByName(String name) {
        try (Connection conn = MySql.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_TRANSCRIPT_BY_NAME)){
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Transcript transcript = new Transcript(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("content"),
                rs.getTimestamp("date"),
                rs.getTimestamp("lastupdate")
            );

            return ResponseEntity.ok().body(transcript);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Transcript name not found");
        }
    }


    public ResponseEntity<?> getTranscriptById(int id) {
        try (Connection conn = MySql.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_TRANSCRIPT_BY_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Transcript transcript = new Transcript(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("content"),
                rs.getTimestamp("date"),
                rs.getTimestamp("lastupdate")
            );
            
            return ResponseEntity.ok().body(transcript);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Transcript " + id + " not found");
        }
    }
}