package chapter17;

import java.util.ArrayList;
import java.util.List;

import json.*;

public class DBIndexingDemo {
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

        // now let's add the records to a binary search tree so that we can index them by timestamp and then do a range search on the tree to get all records in a certain time range
        System.out.println("Adding " + records.size() + " records to the tree");
        BikeDataRecord.sortCriteria = 4; // sort by dist
        BinarySearchTree<BikeDataRecord> recordTree = new BinarySearchTree<>(records.get(0));
        for (int i = 1; i < records.size(); i++) {
            recordTree.add(records.get(i));
            if (i%1000==0) {
                System.out.println("Added "+i+" records to the tree...");
                System.out.println(recordTree.size());
            }
        }
        System.out.println("Height: " + recordTree.height());
        BikeDataRecord searchRecord = new BikeDataRecord(0, 5.0f, 0, 0, 108.0f, 0, 0, 0, 0, 0, null);
        BinaryTreeNode<BikeDataRecord> hit = recordTree.search(searchRecord);
        while (hit != null) {
            System.out.println("Finding 5.0" + hit);
            hit = recordTree.search(hit.right(), searchRecord);
        }

        // sort by speed ... new, easier way to sort ... but note that it is mutative (i.e., it modifies the original list)
        records.sort((r1, r2)->Float.compare(r2.getSpeed(),r1.getSpeed()));
        List<BikeDataRecord> highspeedsonly = records.stream().filter((r)->r.getSpeed()>16.0f).toList();
        int hrtotal1 = highspeedsonly.stream().map((r)->r.getHeartrate()).reduce(0,Integer::sum);
        System.out.println(highspeedsonly.size());
        System.out.println(hrtotal1/highspeedsonly.size());

        BinarySearchTree<Double> altitudeTree = new BinarySearchTree<>((double) records.get(0).getAlt());
        System.out.println(records.stream().filter((bdr)->bdr.getAlt()<10.0).mapToDouble((r)->{double alt = (double) r.getAlt(); altitudeTree.add(alt); return alt;}).average());
    }
}
