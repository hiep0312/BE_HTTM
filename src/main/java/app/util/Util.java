package app.util;


public class Util {
    public static String getTranscriptQuery(String table, int startIdx, int cnt, String sortBy, boolean ascend) {
        return "SELECT * FROM " + table + " Order by " + sortBy + (ascend ? " asc" : " desc") + " LIMIT " + cnt + " OFFSET " + startIdx;
    }
}
