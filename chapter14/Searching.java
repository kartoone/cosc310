package chapter14;

import java.util.ArrayList;
import java.util.Iterator;

import json.BikeDataRecord;

public class Searching {
    
    public static ArrayList<BikeDataRecord> linearSearch(ArrayList<BikeDataRecord> haystack, long needle) {
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
    public static ArrayList<BikeDataRecord> linearSearch(int val, int index) {
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
    
    public static ArrayList<BikeDataRecord> linearSearch(float val, int index) {
        // to do: search by the other fields that are floats
        return null;
    }

    // no need for index b/c we are only searching by timestamp (it's the only long field!)
    public static ArrayList<BikeDataRecord> binarySearch(ArrayList<BikeDataRecord> haystack, int start, int end, long needle) {
        if (start <= end) {
            int mid = (start + end) / 2;
            if (haystack.get(mid).getTimestamp() == needle) {
                ArrayList<BikeDataRecord> hits = new ArrayList<>();
                hits.add(haystack.get(mid));
                // look left
                int i = mid-1;
                while (i>=0 && haystack.get(i).getTimestamp()==needle) {
                    hits.add(haystack.get(i));
                    i--;
                }
                // look right
                i = mid+1;
                while (i<haystack.size() && haystack.get(i).getTimestamp()==needle) {
                    hits.add(haystack.get(i));
                    i++;
                }
                return hits;
            } else if (haystack.get(mid).getTimestamp() > needle) {
                return binarySearch(haystack, start, mid-1, needle);
            } else {
                return binarySearch(haystack, mid+1, end, needle);
            }
        } else {
            return new ArrayList<>(); // not found
        }
    }

    // inclusive on BOTH ends because we are not talking about positions
    public static ArrayList<BikeDataRecord> binarySearch(ArrayList<BikeDataRecord> haystack, int start, int end, long min_needle, long max_needle) {
        if (start <= end) {
            int mid = (start + end) / 2;
            if (haystack.get(mid).getTimestamp() >= min_needle && haystack.get(mid).getTimestamp() <= max_needle) {
                ArrayList<BikeDataRecord> hits = new ArrayList<>();
                hits.add(haystack.get(mid));
                // look left
                int i = mid-1;
                while (i>=0 && haystack.get(i).getTimestamp()>=min_needle && haystack.get(i).getTimestamp()<=max_needle) {
                    hits.add(haystack.get(i));
                    i--;
                }
                // look right
                i = mid+1;
                while (i<haystack.size() && haystack.get(i).getTimestamp()>=min_needle && haystack.get(i).getTimestamp()<=max_needle) {
                    hits.add(haystack.get(i));
                    i++;
                }
                return hits;
            } else if (haystack.get(mid).getTimestamp() > max_needle) {
                return binarySearch(haystack, start, mid-1, min_needle, max_needle);
            } else {
                return binarySearch(haystack, mid+1, end, min_needle, max_needle);
            }
        } else {
            return new ArrayList<>(); // not found
        }
    }

    public static ArrayList<BikeDataRecord> binarySearch(ArrayList<BikeDataRecord> haystack, long needle) {
        return binarySearch(haystack, 0, haystack.size()-1, needle);
    }

    public static ArrayList<BikeDataRecord> binarySearch(ArrayList<BikeDataRecord> haystack, long min_needle, long max_needle) {
        return binarySearch(haystack, 0, haystack.size()-1, min_needle, max_needle);
    }

    public static ArrayList<BikeDataRecord> binarySearch(ArrayList<BikeDataRecord> haystack, int start, int end, int needle, int index) {
        if (start <= end) {
            int mid = (start + end) / 2;
            int val;
            BikeDataRecord r = haystack.get(mid);
            switch (index) {
                case 2: val = r.getHeartrate(); break;
                case 7: val = r.getPow(); break;
                case 8: val = r.getCad(); break;
                case 9: val = r.getDegC(); break;
                default: val = 0;
            }
            if (val == needle) {
                ArrayList<BikeDataRecord> hits = new ArrayList<>();
                hits.add(r);
                // look left
                int i = mid-1;
                if (i>=0) {
                    r = haystack.get(i);
                    switch (index) {
                        case 2: val = r.getHeartrate(); break;
                        case 7: val = r.getPow(); break;
                        case 8: val = r.getCad(); break;
                        case 9: val = r.getDegC(); break;
                        default: val = 0;
                    }
                }
                while (i>=0 && val==needle) {
                    hits.add(r);
                    i--;
                    if (i>=0) {
                        r = haystack.get(i);
                        switch (index) {
                            case 2: val = r.getHeartrate(); break;
                            case 7: val = r.getPow(); break;
                            case 8: val = r.getCad(); break;
                            case 9: val = r.getDegC(); break;
                            default: val = 0;
                        }
                    }
                }
                // look right
                i = mid+1;
                if (i<haystack.size()) {
                    r = haystack.get(i);
                    switch (index) {
                        case 2: val = r.getHeartrate(); break;
                        case 7: val = r.getPow(); break;
                        case 8: val = r.getCad(); break;
                        case 9: val = r.getDegC(); break;
                        default: val = 0;
                    }
                }
                 while (i<haystack.size() && val==needle) {
                    hits.add(r);
                    i++;
                    if (i<haystack.size()) {
                        r = haystack.get(i);
                        switch (index) {
                            case 2: val = r.getHeartrate(); break;
                            case 7: val = r.getPow(); break;
                            case 8: val = r.getCad(); break;
                            case 9: val = r.getDegC(); break;
                            default: val = 0;
                        }
                    }
                }
                return hits;
            } else if (val > needle) {
                return binarySearch(haystack, start, mid-1, needle, index);
            } else {
                return binarySearch(haystack, mid+1, end, needle, index);
            }
        } else {
            return new ArrayList<>(); // not found
        }
    }

    public static ArrayList<BikeDataRecord> binarySearch(ArrayList<BikeDataRecord> haystack, int needle, int index) {
        return binarySearch(haystack, 0, haystack.size()-1, needle, index);
    }

}
