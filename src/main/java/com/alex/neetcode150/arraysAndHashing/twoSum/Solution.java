package com.alex.neetcode150.arraysAndHashing.twoSum;

import java.util.Arrays;
import java.util.HashMap;

// Brute Force
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }
}


// HashMap (2 pass)
class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> valueToIndex = new HashMap<>();

        // add each value to the hashmap as a key, and its index as the value
        for (int i = 0; i < nums.length; i++) {
            valueToIndex.put(nums[i], i);
        }

        // find difference between current value and target, and search for it in hashmap keys
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];

            if (valueToIndex.containsKey(diff)) {
                int indexOfDiff = valueToIndex.get(diff);
                if (indexOfDiff != i) return new int[] {i, valueToIndex.get(diff)};
            }
        }

        return null;
    }
}


// HashMap (1 pass)
class Solution3 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> diffToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];

            if (diffToIndex.containsKey(curr))  return new int[] {diffToIndex.get(curr), i};

            int difference = target - curr;
            diffToIndex.put(difference, i);
        }

        return null;
    }
}


class Test {
    public static void main(String[] args) {

        int[] nums = {3, 2, 4};
        int target = 6;

        Solution3 solution = new Solution3();

        System.out.println(     Arrays.toString(solution.twoSum(nums, target)    )   );
    }
}
