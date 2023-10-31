package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.model.Sample;

public class DatasetSampleDao {
    TranscriptDao transcriptDao = new TranscriptDao();
    DatasetDAO datasetDAO = new DatasetDAO();

    private static final String GET_SAMPLE_BY_DATASET_ID = "select s.id, s.name, s.audioId, s.transcriptId, s.date, s.lastupdate from sample as s, sampledataset where sampledataset.datasetid = ? and s.id = sampledataset.sampleId limit ? offset ?";

    public List<Sample> getSampleFromDataset(int start_idx, int count, int datasetId) {
        List<Sample> list = new ArrayList<>();

        try (Connection conn = MySql.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("select count(*) as total from sample as s, sampledataset as sd where s.id = sd.sampleID and sd.datasetId = ?");
            ps.setInt(1, datasetId);
            ResultSet rs = ps.executeQuery();
            rs.next();

            int total = rs.getInt("total");
            if (start_idx >= total) {
                System.out.println("No sample");
                return list;
            }

            int fixed_cnt = Math.min(count, total - start_idx);

            ps = conn.prepareStatement(GET_SAMPLE_BY_DATASET_ID);
            ps.setInt(1, datasetId);
            ps.setInt(2, fixed_cnt);
            ps.setInt(3, start_idx);

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Sample(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("audioId"),
                    rs.getInt("transcriptId"),
                    rs.getTimestamp("date"),
                    rs.getTimestamp("lastupdate")
                    ));
                
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Integer> getListIdSample(int datasetId) {
        List<Integer> list = new ArrayList<>();

        try (Connection conn = MySql.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM sampledataset WHERE datasetId = ?");
            ps.setInt(1, datasetId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("sampleId"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
