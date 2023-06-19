package com.alex.neetcode150.arraysAndHashing.containsDuplicate;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {1, 2, 3, 4, 5};      // No duplicates
        int[] nums2 = {1, 2, 3, 1};         // Contains duplicate (1)
        int[] nums3 = {5, 5, 6, 7, 8};      // Contains duplicate (5)
        int[] nums4 = {1, 1, 1, 1, 1};      // Contains duplicate (1)

        System.out.println(solution.containsDuplicate(nums1));   // Expected output: false
        System.out.println(solution.containsDuplicate(nums2));   // Expected output: true
        System.out.println(solution.containsDuplicate(nums3));   // Expected output: true
        System.out.println(solution.containsDuplicate(nums4));   // Expected output: true
    }
}

