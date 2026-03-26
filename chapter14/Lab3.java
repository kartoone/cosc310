package chapter14;

import java.util.ArrayList;
import java.util.List;

import json.*;

public class Lab3 {
    public static void main(String[] args) throws Exception {
        ArrayList<BikeDataRecord> records = BikeDataReader.parse("json/day2.json");
        System.out.println(records.size());
        System.out.println(records.get(20));
        List<BikeDataRecord> sortedRecords = Sorting.mergeSort(records);
        System.out.println(records.get(0));


    }
}
