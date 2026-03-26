package json;

import java.util.Iterator;

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
    private float speed; // rider speed in m/s
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
        // you need to do indices 2-9
        alt = Float.parseFloat(recjson.getString(4));

        // now let's parse in the vehicle data (from the radar array)
        JSONArray jsonRadarArray = recjson.getJSONArray(10);
        if (jsonRadarArray.length()>0) {
            radarArray = new int[jsonRadarArray.length()][2];
            Iterator<Object> it = jsonRadarArray.iterator();
            int ri = 0;
            while (it.hasNext()) {
                JSONArray vehData = (JSONArray) it.next();
                radarArray[ri][0] = vehData.getInt(0); // read range (m) into position 0 
                if (vehData.length()>1) {
                    // radar disabled records  will have a -1 in position 0 for the radar array first entry
                    radarArray[ri++][1] = vehData.getInt(1); // read speed (m/s) into position 1
                }
            }
        }

    }

    @Override
    public int compareTo(BikeDataRecord o) {
        return Float.compare(alt, o.alt);
    }

    @Override
    public String toString() {
        return "BikeDataRecord [timestamp=" + timestamp + ", distance=" + distance + ", heartrate=" + heartrate
                + ", speed=" + speed + ", alt=" + alt + ", lat=" + lat + ", lng=" + lng + ", pow=" + pow
                + ", cad=" + cad + ", degC=" + degC + "]";
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getDistance() {
        return distance;
    }

    public int getHeartrate() {
        return heartrate;
    }

    public float getSpeed() {
        return speed;
    }

    public float getAlt() {
        return alt;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public int getPow() {
        return pow;
    }

    public int getCad() {
        return cad;
    }

    public int getDegC() {
        return degC;
    }

    public int[][] getRadarArray() {
        return radarArray;
    }

    

}
