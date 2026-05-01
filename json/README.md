COSC 310

# Cycling Telemetry Data Analysis

This project analyzes thousands of GPS track points generated from a week of cycling activities. The program processes raw telemetry JSON data and utilizes advanced computer science sorting and searching algorithms to extract meaningful performance insights.

## Problem Formulation

1. **Peak Performance Identification:** How can we rank top 10 moments of highest power output to analyze the rider's peak physical exertion?
2. **Anomaly Investigation:** How can we efficiently retrieve the specific telemetry data for a precise target Garmin timestamp without scanning the entire week's dataset?

## Algorithm Selection & Complexity Analysis

### 1. Merge Sort (Sorting by Power)
To solve the Peak Performance problem, the data is passed through a **Merge Sort** algorithm, ordering the points descending by the `power` field.
* **Justification:** Merge Sort is a divide-and-conquer algorithm that is highly stable. Given the size of the dataset, an efficient algorithm is required. 
* **Time Complexity:** `O(N log N)` in all cases (worst, average, and best).
* **Space Complexity:** `O(N)` because it requires auxiliary space for the temporary sub-lists during the merging process.

### 2. Binary Search (Searching by Timestamp)
To solve the Anomaly Investigation problem, the data is queried using a **Binary Search**. 
* **Justification:** Cycling data is inherently sequential and recorded chronologically. Because the dataset is already sorted by time, using an `O(N)` linear search to find a single timestamp is highly inefficient. Binary Search drastically reduces lookup times by repeatedly halving the search space.
* **Time Complexity:** `O(log N)` which allows us to find a single point out of thousands in mere fractions of a millisecond.
* **Space Complexity:** `O(1)` as the iterative implementation only uses a few pointers (`left`, `right`, `mid`) in memory.

## Final Output / Results

When executed against the telemetry dataset, the system outputs the following analytical results:

**Search Query Executed:** Target Garmin Timestamp `1142166966`
```text
Loading week-long telemetry data...

Total records loaded for the week: 223983

--- SEARCH RESULT (Anomaly Investigation) ---
Found Record: BikeDataRecord [timestamp=1142166966, distance=930.56, heartrate=103, speed=7.81, alt=24.6, lat=37.27334, lng=-77.30898, pow=169, cad=66, degC=22.0]

--- TOP 10 POWER OUTPUTS OF THE WEEK (Peak Exertion) ---
1. BikeDataRecord [timestamp=1142010515, distance=282985.34, heartrate=107, speed=10.628, alt=15.8, lat=39.53334, lng=-76.1203, pow=705, cad=97, degC=25.0]
2. BikeDataRecord [timestamp=1142015078, distance=312244.06, heartrate=113, speed=7.147, alt=49.6, lat=39.39939, lng=-76.40267, pow=697, cad=83, degC=27.0]
3. BikeDataRecord [timestamp=1142010514, distance=282974.88, heartrate=107, speed=9.965, alt=15.8, lat=39.53339, lng=-76.1202, pow=685, cad=91, degC=25.0]
4. BikeDataRecord [timestamp=1142010516, distance=282996.03, heartrate=107, speed=10.758, alt=15.6, lat=39.53328, lng=-76.1204, pow=663, cad=102, degC=25.0]
5. BikeDataRecord [timestamp=1142004520, distance=246165.72, heartrate=89, speed=5.384, alt=45.4, lat=39.52896, lng=-75.81402, pow=654, cad=82, degC=25.0]
6. BikeDataRecord [timestamp=1142015079, distance=312251.2, heartrate=112, speed=7.782, alt=49.6, lat=39.39935, lng=-76.40273, pow=620, cad=91, degC=27.0]
7. BikeDataRecord [timestamp=1142074257, distance=106128.04, heartrate=95, speed=5.561, alt=130.8, lat=38.89236, lng=-77.22498, pow=619, cad=80, degC=10.0]
8. BikeDataRecord [timestamp=1142010513, distance=282965.22, heartrate=107, speed=9.601, alt=16.0, lat=39.53344, lng=-76.12011, pow=610, cad=86, degC=24.0]
9. BikeDataRecord [timestamp=1142010512, distance=282955.94, heartrate=107, speed=8.901, alt=16.0, lat=39.53349, lng=-76.12001, pow=601, cad=81, degC=24.0]
10. BikeDataRecord [timestamp=1142006424, distance=254764.4, heartrate=80, speed=6.587, alt=15.2, lat=39.59988, lng=-75.82765, pow=600, cad=73, degC=24.0]