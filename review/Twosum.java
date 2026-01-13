package review;

// doubly nested loop to find the two values that add up to target
// added tuesday, 1/13/26

public class Twosum {
    
    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2]; // this array will hold our result indices
        for (int i=0; i<nums.length-1; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i; // replace the default values in the array
                    result[1] = j; // with the values of the indices
                    return result;
                }
            }
        }
        System.out.println("invalid input or we did something wrong");
        return result;

    }

}
