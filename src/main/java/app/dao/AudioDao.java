package app.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
// import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import app.model.Audio;
import app.util.FileCopier;

public class AudioDao {
    // public static String parent_folder = "D:/SourceCode/tts/BE/";"D:\\SourceCode\\Java\\tts\\BE\\Audios\\"
    public static String parent_folder;

    private static final String GET_COUNT_AUDIOS = "SELECT COUNT(*) as total FROM audio";
    private static final String GET_AUDIO_BY_ID = "SELECT * FROM audio WHERE id = ?";
    private static final String GET_AUDIOS = "SELECT * FROM audio LIMIT ? OFFSET ?";
    private static final String ADD_AUDIO = "INSERT INTO audio (name, path) VALUES (?, ?)";
    private static final String DELETE_AUDIO = "DELETE FROM audio WHERE id = ?";
    private static final String UPDATE_AUDIO = "UPDATE audio SET name = ?, path = ?, lastupdate = CURRENT_TIMESTAMP WHERE id = ?";
    private static final String GET_AUDIO_BY_NAME = "SELECT * FROM audio WHERE name = ?";
    
    public AudioDao() {
        parent_folder = System.getProperty("user.dir") + "\\Audios\\";
    }


    public List<Audio> getAudios(int start_idx, int cnt) {
        List<Audio> audios = new ArrayList<Audio>();

        try (Connection conn = MySql.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(GET_COUNT_AUDIOS);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int total = rs.getInt("total");
            if (start_idx >= total) {
                System.out.println("No transcript");
                return audios;
            }

            int fixed_cnt = Math.min(cnt, total - start_idx);

            ps = conn.prepareStatement(GET_AUDIOS);
            ps.setInt(1, fixed_cnt);
            ps.setInt(2, start_idx);
            rs = ps.executeQuery();

            while (rs.next()) {
                audios.add(new Audio(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("path"),
                        rs.getTimestamp("date"),
                        rs.getTimestamp("lastupdate")));
            }

            ps.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return audios;
    }

    public ResponseEntity<byte[]> getAudioFileById(int id) {
        try (Connection conn = MySql.getConnection();
                PreparedStatement ps = conn.prepareStatement(GET_AUDIO_BY_ID);
                ) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Audio audio = new Audio(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("path"),
                    rs.getTimestamp("date"),
                    rs.getTimestamp("lastupdate"));

            ps.close();
            rs.close();
            conn.close();


            byte[] audioData = Files.readAllBytes(Paths.get(parent_folder + audio.getPath()));
           
            ResponseEntity<byte[]> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header("Content-Disposition", "inline; filename=" + parent_folder + audio.getPath())
            .body(audioData);
            return response;
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("return audio failed".getBytes());
        }
    }

    public ResponseEntity<?> addAudio(String name, MultipartFile audio) {
        try (Connection conn = MySql.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(ADD_AUDIO);

            String path = AudioDao.parent_folder + audio.getOriginalFilename();
            // File file = new File(path);
            // file.createNewFile();

            System.out.println(path);
            // String query = "INSERT INTO audio (name, path) VALUES (?, ?)";

            FileCopier.copyFile(audio.getInputStream(), Paths.get(path));
            Files.copy(audio.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);

            ps.setString(1, name);
            ps.setString(2, audio.getOriginalFilename());

            ps.executeUpdate();

            ps.close();
            conn.close();

            return ResponseEntity.ok().body("Add audio successfully " + name + " " + audio.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Add audio failed");
        }
    }

    public ResponseEntity<?> deleteAudio(int id) {
        try (Connection conn = MySql.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM sample WHERE audioId = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            ps = conn.prepareStatement(DELETE_AUDIO);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            conn.close();

            return ResponseEntity.ok().body("Delete audio successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Delete audio " + id + " failed");
        }
    }

    public ResponseEntity<?> editAudio(int id, String name, MultipartFile audio) {
        try (Connection conn = MySql.getConnection();
                PreparedStatement ps = conn.prepareStatement(UPDATE_AUDIO)) {

            String path = AudioDao.parent_folder + audio.getOriginalFilename();
            // String query = "INSERT INTO audio (name, path) VALUES (?, ?)";
            FileCopier.copyFile(audio.getInputStream(), Paths.get(path));
            ps.setString(1, name);
            ps.setString(2, audio.getOriginalFilename());
            ps.setInt(3, id);
            ps.executeUpdate();

            ps.close();
            conn.close();

            return ResponseEntity.ok().body("Edit audio successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Edit audio failed");
        }
    }

    public ResponseEntity<?> getAudioByName(String name) {
        try (Connection conn = MySql.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_AUDIO_BY_NAME)) {
            
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return ResponseEntity.internalServerError().body("No audio");
            }

            Audio audio = new Audio(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("path"),
                rs.getTimestamp("date"),
                rs.getTimestamp("lastupdate")
            );

            rs.close();
            ps.close();
            conn.close();

            return ResponseEntity.ok().body(audio);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Audio name not found");
        }
    }


    public ResponseEntity<?> getAudioById(int id) {
        try (Connection conn = MySql.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_AUDIO_BY_ID)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Audio audio = new Audio(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("path"),
                rs.getTimestamp("date"),
                rs.getTimestamp("lastupdate")
            );

            return ResponseEntity.ok().body(audio);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Audio " + id + " not found");
        }
    }
}
