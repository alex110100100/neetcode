package com.alex.neetcode150.twoPointers.containerWithMostWater;

class Solution {
    public int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int maxArea = 0;

        while (left < right) {
            int leftHeight = heights[left];
            int rightHeight = heights[right];
            int width = right - left;
            int area = leftHeight < rightHeight ? leftHeight * width : rightHeight * width;
            maxArea = Math.max(maxArea, area);

            if (leftHeight < rightHeight)
                left++;
            else if (rightHeight < leftHeight)
                right--;
            else {
                left++;
                right--;
            }
        }

        return maxArea;
    }
}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic example
        int[] heights1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int expected1 = 49;
        int result1 = solution.maxArea(heights1);
        System.out.println(result1 == expected1 ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2: Edge case with empty array
        int[] heights2 = {};
        int expected2 = 0;
        int result2 = solution.maxArea(heights2);
        System.out.println(result2 == expected2 ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3: Edge case with single element
        int[] heights3 = {5};
        int expected3 = 0;
        int result3 = solution.maxArea(heights3);
        System.out.println(result3 == expected3 ? "Test Case 3 Passed" : "Test Case 3 Failed");

        // Test Case 4: Edge case with two elements
        int[] heights4 = {3, 7};
        int expected4 = 3;
        int result4 = solution.maxArea(heights4);
        System.out.println(result4 == expected4 ? "Test Case 4 Passed" : "Test Case 4 Failed");
    }
}
