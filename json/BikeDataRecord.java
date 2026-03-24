package json;

import org.json.JSONArray;

public class BikeDataRecord implements Comparable<BikeDataRecord> {

    // UTC timetamp as a Garmin timestamp - add this 631065600 to convert to UTC Time
    // Distance from the start of the ride (measured in meters)
    // Heartrate
    // Speed (m/s)
    // Altitude (m)
    // Latitude
    // Longitude
    // Power (watts)
    // Cadence (rpm)
    // Temperature (degC)
    // Radar array (which itself is an array of cars, with each car listing the distance behind (m) and relative approach speed (m/s))
    private long timestamp;
    private float distance;
    private int heartrate;
    private float speed; // m/s
    private float alt; // m
    private float lat; // m
    private float lng; // m
    private int pow; // m
    private int cad; // m
    private int degC; // m
    private int[][] radarArray = null; // no cars are coming or going so we can get the EXACT sized array when we parse in the data

    public BikeDataRecord(JSONArray recjson) {
        timestamp = Long.parseLong(recjson.getString(0));
        distance = Float.parseFloat(recjson.getString(1));
    }

    @Override
    public int compareTo(BikeDataRecord o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
