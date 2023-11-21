package com.alex.neetcode150.binarySearch.findMinimumInRotatedSortedArray;

class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;

        while (left <= right) {
            mid = left + (right - left)/2;

            if (nums[right] > nums[mid]) {
                right = mid;                   // NB: we don't do right = mid - 1; here because mid could be the min
            } else {
                left = mid + 1;                // NB: we can do left = mid + 1; here because we already know that
            }                                   // at least 1 num is less than mid (bc we know nums[right is less]
        }
        return nums[mid];
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {3, 4, 5, 1, 2};      // Rotated sorted array
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2}; // Rotated sorted array
        int[] nums3 = {1, 2, 3};            // Sorted array (not rotated)
        int[] nums4 = {5, 6, 7, 8, 9, 1, 2, 3}; // Rotated sorted array

        System.out.println(solution.findMin(nums1));   // Expected output: 1
        System.out.println(solution.findMin(nums2));   // Expected output: 0
        System.out.println(solution.findMin(nums3));   // Expected output: 1
        System.out.println(solution.findMin(nums4));   // Expected output: 1
    }
}


