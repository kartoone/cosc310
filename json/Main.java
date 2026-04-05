package json; // Assuming you are keeping everything in the json package as provided

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 1. Core Analysis Engine containing Algorithms
class TelemetryAnalyzer {

    // Problem 1: Merge Sort implementation.
    // We pass a boolean 'descending' so we can easily get the TOP results (highest numbers first).

    public static void mergeSort(List<BikeDataRecord> points, boolean descending) {
        if (points.size() <= 1) return;

        int mid = points.size() / 2;
        List<BikeDataRecord> left = new ArrayList<>(points.subList(0, mid));
        List<BikeDataRecord> right = new ArrayList<>(points.subList(mid, points.size()));

        mergeSort(left, descending);
        mergeSort(right, descending);

        merge(points, left, right, descending);
    }

    private static void merge(List<BikeDataRecord> points, List<BikeDataRecord> left, List<BikeDataRecord> right, boolean descending) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            int comparison = left.get(i).compareTo(right.get(j));
            
            // If descending is true, we want the larger item first. 
            // compareTo returns > 0 if left > right.
            boolean condition = descending ? (comparison >= 0) : (comparison <= 0);

            if (condition) {
                points.set(k++, left.get(i++));
            } else {
                points.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            points.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            points.set(k++, right.get(j++));
        }
    }

    // Problem 2: Binary Search implementation to find a specific timestamp

    public static BikeDataRecord binarySearchByTimestamp(List<BikeDataRecord> points, long targetTimestamp) {
        int left = 0;
        int right = points.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            BikeDataRecord midPoint = points.get(mid);

            if (midPoint.getTimestamp() == targetTimestamp) {
                return midPoint;
            }

            if (midPoint.getTimestamp() < targetTimestamp) {
                left = mid + 1; // Look in the right half
            } else {
                right = mid - 1; // Look in the left half
            }
        }
        return null; // Timestamp not found
    }
}

// 2. Main Execution
public class Main {
    public static void main(String[] args) {
        // 1. Array of all files for the week
        String[] files = {
            "C:\\Users\\Drews\\OneDrive\\Samford\\Junmore\\310 & 325\\AdvPrgmCncpt\\cosc310APC\\json\\day1.json",
            "C:\\Users\\Drews\\OneDrive\\Samford\\Junmore\\310 & 325\\AdvPrgmCncpt\\cosc310APC\\json\\day2.json",
            "C:\\Users\\Drews\\OneDrive\\Samford\\Junmore\\310 & 325\\AdvPrgmCncpt\\cosc310APC\\json\\day3.json",
            "C:\\Users\\Drews\\OneDrive\\Samford\\Junmore\\310 & 325\\AdvPrgmCncpt\\cosc310APC\\json\\day4.json"
        };

        // This will hold the combined data for the entire week
        ArrayList<BikeDataRecord> allTrackPoints = new ArrayList<>();

        System.out.println("Loading week-long telemetry data...");

        // 2. Loop through all 4 files and combine them
        for (String file : files) {
            try {
                ArrayList<BikeDataRecord> dailyPoints = BikeDataReader.parse(file);
                allTrackPoints.addAll(dailyPoints); // Appends the daily data to our master list
            } catch (IOException e) {
                System.err.println("Error reading " + file + ". Make sure the file exists in your json folder.");
            } catch (Exception e) {
                System.err.println("An unexpected error occurred parsing " + file + ": " + e.getMessage());
            }
        }

        System.out.println("\nTotal records loaded for the week: " + allTrackPoints.size() + "\n");

        if (!allTrackPoints.isEmpty()) {
            
            // --- Anomaly Investigation: Binary Search ---
            // Let's pick a timestamp that we suspect might be in day3 or day4
            long targetGarminTime = 1142166966L; 
            System.out.println("--- SEARCH RESULT (Anomaly Investigation) ---");
            
            BikeDataRecord.sortCriteria = 0; // 0 = timestamp
            BikeDataRecord foundPoint = TelemetryAnalyzer.binarySearchByTimestamp(allTrackPoints, targetGarminTime);
            
            if (foundPoint != null) {
                System.out.println("Found Record: " + foundPoint.toString());
            } else {
                System.out.println("Record not found for timestamp: " + targetGarminTime);
            }

            // --- Peak Exertion: Sorting to find Top 10 Power outputs ---
            System.out.println("\n--- TOP 10 POWER OUTPUTS OF THE WEEK (Peak Exertion) ---");
            
            BikeDataRecord.sortCriteria = 7; // 7 = power
            TelemetryAnalyzer.mergeSort(allTrackPoints, true); // true = descending order
            
            int limit = Math.min(10, allTrackPoints.size());
            for (int i = 0; i < limit; i++) {
                System.out.println((i + 1) + ". " + allTrackPoints.get(i).toString());
            }
        } else {
            System.out.println("No data was loaded. Exiting analysis.");
        }
    }
}