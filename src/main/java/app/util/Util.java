package app.util;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class Util {
    public static String getTranscriptQuery(String table, int startIdx, int cnt, String sortBy, boolean ascend) {
        return "SELECT * FROM " + table + " Order by " + sortBy + (ascend ? " asc" : " desc") + " LIMIT " + cnt + " OFFSET " + startIdx;
    }

    public static ResponseEntity<byte[]> getAudioResponse(byte[] audioFile, String name) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header("Content-Disposition", "inline; filename=" + name + ".wav")
            .body(audioFile);
    }
}
