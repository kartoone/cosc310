package review;

import java.util.ArrayList;

// doubly nested loop to find the two values that add up to target
// added tuesday, 1/13/26

public class Twosum {

    protected ArrayList<int[]> history = new ArrayList<>();

    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2]; // this array will hold our result indices
        for (int i=0; i<nums.length-1; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i; // replace the default values in the array
                    result[1] = j; // with the values of the indices
                    history.add(result);
                    return result;
                }
            }
        }
        System.out.println("invalid input or we did something wrong");
        return result;

    }

    public static void main(String[] args) {
        Twosum ts = new Twosum();
        int[] nums = new int[] { 4, 6, 2, 1, 7};
        /* int[] nums = new int[5];
        nums[0] = 4;
        nums[1] = 6;
        nums[2] = 2;
        nums[3] = 1;
        nums[4] = 7; */
        int[] result = ts.twoSum(nums,9);
        System.out.println(java.util.Arrays.toString(result));
    }

}
