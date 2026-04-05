package json;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.json.JSONArray;

public class BikeDataReader {
    public static ArrayList<BikeDataRecord> parse(String filepath) throws IOException, OutOfMemoryError {
        File f = new File(filepath);
        String fileContents = Files.readString(f.toPath());
        JSONArray jsonArray = new JSONArray(fileContents);
        ArrayList<BikeDataRecord> records = new ArrayList<>();
        Iterator<Object> it = jsonArray.iterator();
        while(it.hasNext()) {
            JSONArray nextRecord = (JSONArray) it.next();
            records.add(new BikeDataRecord(nextRecord));
        }
        return records;
    }
}
