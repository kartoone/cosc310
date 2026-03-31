package chapter14;

import java.util.ArrayList;
import java.util.List;

import json.*;

public class Lab3 {
    public static void main(String[] args) throws Exception {
        ArrayList<BikeDataRecord> records = new ArrayList<>();
        ArrayList<BikeDataRecord> records1 = BikeDataReader.parse("json/day1.json");
        ArrayList<BikeDataRecord> records2 = BikeDataReader.parse("json/day2.json");
        ArrayList<BikeDataRecord> records3 = BikeDataReader.parse("json/day3.json");
        ArrayList<BikeDataRecord> records4 = BikeDataReader.parse("json/day4.json");
        records.addAll(records1);
        records.addAll(records2);
        records.addAll(records3);
        records.addAll(records4);
        System.out.println(records.size());
        BikeDataRecord.sortCriteria = 0;
        List<BikeDataRecord> sortedRecords = Sorting.mergeSort(records);
        System.out.println(sortedRecords.get(0));
        System.out.println(sortedRecords.get(1));
        System.out.println(sortedRecords.get(2));
        System.out.println("...");
        int count = 0;
        for (BikeDataRecord bikeDataRecord : sortedRecords) {
            if (bikeDataRecord.getHeartrate()>0) {
                System.out.println(bikeDataRecord);
                count++;
            }
            if (count>10)
                break;
        }

        ArrayList<BikeDataRecord> lunchday1  = Searching.binarySearch((ArrayList<BikeDataRecord>)sortedRecords, 1141920754L, 1141923454L);
        System.out.println(lunchday1.size());
        int hrtotal = lunchday1.stream().map(BikeDataRecord::getHeartrate).reduce(0, Integer::sum);
        int hravg = hrtotal/lunchday1.size();
        System.out.println(hravg);

        ArrayList<BikeDataRecord> lunchday3  = Searching.binarySearch((ArrayList<BikeDataRecord>)sortedRecords, 1141920754L+72800, 1141923454L+72800);
        System.out.println(lunchday3.size());
        int hrtotal3 = lunchday3.stream().map(BikeDataRecord::getHeartrate).reduce(0, Integer::sum);
        int hravg3 = hrtotal3/lunchday3.size();
        System.out.println(hravg3);
        int[][] radarArrayTest = new int[][] { new int[] {10, 3}, new int[] {20, 6, 7, 8, 9}, new int[] { 50, 10} };
        System.out.println(radarArrayTest[1].length);


    }
}
