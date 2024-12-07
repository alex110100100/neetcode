package com.alex.neetcode150.twoPointers.TrappingRainWater;

/*
    To solve this problem, just think about the water in "columns".
    i.e. at each index, find out what amount of water can be held in the column for that index.
    To do that, you need to think about what limits the water being held in this column.
    It isn't the walls immediately to its left and right, but rather the highest wall anywhere to its
    left and the highest wall anywhere to its right. The amount of water held by a column will determined by the
    smaller of those 2 maxes, minus the height of the wall in this column itself, if there is one.

    So, for any column i, the amount of water in it will be: Min(maxLeftHeight, maxRightHeight) - height(i)
 */


//Note: Although I have it within the "twoPointers" directory, this first approach below doesn't actually use 2 pointers.
// It's easier to understand this solution first, and then move onto the 2 pointers approach afterwards.
class ArraysSolution {
    public int trap(int[] height) {
        // create array of max height left of index i
        int[] maxLeftHeights = new int[height.length];

        for (int i = 1; i < height.length; i++) {
            maxLeftHeights[i] = Math.max(maxLeftHeights[i - 1], height[i - 1]);
        }

        // create array of max height right of index i
        int[] maxRightHeights = new int[height.length];

        for (int i = height.length - 2; i >= 0; i--) {
            maxRightHeights[i] = Math.max(maxRightHeights[i + 1], height[i + 1]);
        }

        // find water in each column and add it to total
        int totalRainfall = 0;

        for (int i = 0; i < height.length; i++) {
            int waterInColumn = Math.min(maxLeftHeights[i], maxRightHeights[i]) - height[i];

            if (waterInColumn > 0)
                totalRainfall += waterInColumn;
        }

        return totalRainfall;
    }
}


class TwoPointerSolution {
    public int trap(int[] height) {

    /*
        The 2 pointers approach to this problem works similarly to the arrays approach, but we save space this way.

        This time, we create a pointer at either end and move them inward.
        As we go, we keep track of the maxLeftHeight and maxRightHeight so far, using 2 variables this time, instead of 2 arrays.

        We continuously compare maxLeftHeight and maxRightHeight.
        While maxLeftHeight is lower than maxRightHeight, we move our left pointer inwards (or right pointer if the opposite is true),
        and calculate the area of water in that column.
            Remember in the arrays approach, we did waterInColumn = Min(maxLeftHeight, maxRightHeight) - height(i) ?
            It will be the same this time, but instead of i, it will be the left pointer (or right if maxRightHeight was the lower one).

     */

        int left = 0;
        int right = height.length - 1;

        int maxLeftHeight = height[left];
        int maxRightHeight = height[right];

        int totalRainfall = 0;

        while (left < right) {
            if (maxLeftHeight < maxRightHeight) {
                left++;
                int leftHeight = height[left];

                if (leftHeight > maxLeftHeight) {
                    maxLeftHeight = leftHeight;
                } else {
                    int rainfallForColumn = maxLeftHeight - leftHeight;
                    totalRainfall += rainfallForColumn;
                }
            } else {
                right--;
                int rightHeight = height[right];

                if (rightHeight > maxRightHeight) {
                    maxRightHeight = rightHeight;
                } else {
                    int rainfallForColumn = maxRightHeight - rightHeight;
                    totalRainfall += rainfallForColumn;
                }
            }
        }

        return totalRainfall;
    }
}


class Test {
    public static void main(String[] args) {
        TwoPointerSolution solution = new TwoPointerSolution();

        // Test Case 1: Basic valley that can trap water
        int[] input1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        int expected1 = 6;
        int result1 = solution.trap(input1);
        System.out.println(result1 == expected1 ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2: Single element
        int[] input3 = {5};
        int expected3 = 0;
        int result3 = solution.trap(input3);
        System.out.println(result3 == expected3 ? "Test Case 3 Passed" : "Test Case 3 Failed");

        // Test Case 3: No water can be trapped
        int[] input4 = {1,2,3,4,5};
        int expected4 = 0;
        int result4 = solution.trap(input4);
        System.out.println(result4 == expected4 ? "Test Case 4 Passed" : "Test Case 4 Failed");

        // Test Case 4: Symmetric valley
        int[] input5 = {3,0,3};
        int expected5 = 3;
        int result5 = solution.trap(input5);
        System.out.println(result5 == expected5 ? "Test Case 5 Passed" : "Test Case 5 Failed");

        // Test Case 5: Multiple valleys
        int[] input6 = {4,2,3,1,2};
        int expected6 = 2;
        int result6 = solution.trap(input6);
        System.out.println(result6 == expected6 ? "Test Case 6 Passed" : "Test Case 6 Failed");
    }
}

