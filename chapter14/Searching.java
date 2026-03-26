package chapter14;

import java.util.ArrayList;
import java.util.Iterator;

import json.BikeDataRecord;

public class Searching {
    
    public ArrayList<BikeDataRecord> linearSearch(ArrayList<BikeDataRecord> haystack, long needle) {
        ArrayList<BikeDataRecord> hits = new ArrayList<>();
        Iterator<BikeDataRecord> it = haystack.iterator();
        while(it.hasNext()) {
            BikeDataRecord r = it.next();
            if (r.getTimestamp()==needle) {
                hits.add(r);
            }
        }
        return hits;
    }

    // only valid indices are 2, 7, 8, 9
    public ArrayList<BikeDataRecord> linearSearch(int val, int index) {
        ArrayList<BikeDataRecord> hits = new ArrayList<>();
        Iterator<BikeDataRecord> it = hits.iterator();
        while(it.hasNext()) {
            BikeDataRecord r = it.next();
            switch (index) {
                case 2: if (r.getHeartrate()==val) { hits.add(r); } break;
                case 7: if (r.getPow()==val) { hits.add(r); } break;
                case 8: if (r.getCad()==val) { hits.add(r); } break;
                case 9: if (r.getDegC()==val) { hits.add(r); } break;
            }
        }
        return hits;
    }
    
    public ArrayList<BikeDataRecord> linearSearch(float val, int index) {
        // to do: search by the other fields that are floats
        return null;
    }

    public ArrayList<BikeDataRecord> binarySearch(ArrayList<BikeDataRecord> haystact, int start, int end, long needle) {
        return null;
    }

    public ArrayList<BikeDataRecord> binarySearch(ArrayList<BikeDataRecord> haystack, long needle) {
        return binarySearch(haystack, 0, haystack.size()-1, needle);
    }


}
