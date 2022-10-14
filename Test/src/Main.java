public class Main {

    class Solution {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                if (int[i]+int[i+1]==target)
                {
                    System.out.println(i+" "+i+1);
                    break;
                }
            }
        }
    }
}
