package com.alex.neetcode150.heapsAndPriorityQueues.kClosestPointsToOrigin;

import java.util.HashSet;

import java.util.*;

class Solution {

    // Using a min approach is quite intuitive, but less efficient that using a max heap
    public int[][] kClosestMinHeap(int[][] points, int k) {
        // Min heap approach: add all points, then extract k smallest
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            // Compare by distance squared (no need for sqrt since we're just comparing)
            int distA = a[0] * a[0] + a[1] * a[1];
            int distB = b[0] * b[0] + b[1] * b[1];
            return Integer.compare(distA, distB); // Min heap: smaller distances first
        });

        // Add all points to heap
        for (int[] point : points) {
            minHeap.offer(point);
        }

        // Extract k closest points
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }

        return result;
    }


    // Max heap approach is more efficient
    public int[][] kClosestMaxHeap(int[][] points, int k) {
        // Use a max heap to keep track of k closest points
        // We use max heap so we can remove the farthest point when size exceeds k
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            // Compare by distance squared (no need for sqrt since we're just comparing)
            int distA = a[0] * a[0] + a[1] * a[1];
            int distB = b[0] * b[0] + b[1] * b[1];
            return Integer.compare(distB, distA); // Max heap: larger distances first
        });

        for (int[] point : points) {
            maxHeap.offer(point);

            // If heap size exceeds k, remove the farthest point
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Convert heap to array
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }

        return result;
    }
}



class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int testsPassed = 0;
        int totalTests = 8;

        // Test case 1: LeetCode Example 1
        System.out.println("Test 1: LeetCode Example 1 - k=1");
        int[][] points1 = {{1,3},{-2,2}};
        int k1 = 1;
        int[][] expected1 = {{-2,2}}; // Distance: sqrt(8) vs sqrt(10)
        int[][] result1 = solution.kClosestMaxHeap(points1, k1);

        if (arraysEqual(result1, expected1)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + Arrays.deepToString(expected1));
            System.out.println("Got: " + Arrays.deepToString(result1));
        }
        System.out.println();

        // Test case 2: LeetCode Example 2
        System.out.println("Test 2: LeetCode Example 2 - k=2");
        int[][] points2 = {{3,3},{5,-1},{-2,4}};
        int k2 = 2;
        int[][] expected2 = {{3,3},{-2,4}}; // Distances: 18, 26, 20 -> take first 2 smallest
        int[][] result2 = solution.kClosestMaxHeap(points2, k2);

        if (arraysEqualUnordered(result2, expected2)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + Arrays.deepToString(expected2));
            System.out.println("Got: " + Arrays.deepToString(result2));
        }
        System.out.println();

        // Test case 3: k equals total points
        System.out.println("Test 3: k equals total number of points");
        int[][] points3 = {{1,1},{2,2},{3,3}};
        int k3 = 3;
        int[][] expected3 = {{1,1},{2,2},{3,3}}; // All points
        int[][] result3 = solution.kClosestMaxHeap(points3, k3);

        if (arraysEqualUnordered(result3, expected3)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + Arrays.deepToString(expected3));
            System.out.println("Got: " + Arrays.deepToString(result3));
        }
        System.out.println();

        // Test case 4: Points on axes
        System.out.println("Test 4: Points on coordinate axes");
        int[][] points4 = {{0,3},{4,0},{0,-5},{-2,0}};
        int k4 = 2;
        int[][] expected4 = {{-2,0},{0,3}}; // Distances: 9, 16, 25, 4
        int[][] result4 = solution.kClosestMaxHeap(points4, k4);

        if (arraysEqualUnordered(result4, expected4)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + Arrays.deepToString(expected4));
            System.out.println("Got: " + Arrays.deepToString(result4));
        }
        System.out.println();

        // Test case 5: Points with same distance
        System.out.println("Test 5: Points with equal distances");
        int[][] points5 = {{1,1},{-1,-1},{1,-1},{-1,1}};
        int k5 = 2;
        // All points have distance sqrt(2), any 2 are valid
        int[][] result5 = solution.kClosestMaxHeap(points5, k5);

        if (result5.length == 2 && allPointsValid(result5, points5)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: Any 2 points from " + Arrays.deepToString(points5));
            System.out.println("Got: " + Arrays.deepToString(result5));
        }
        System.out.println();

        // Test case 6: Single point
        System.out.println("Test 6: Single point");
        int[][] points6 = {{0,1}};
        int k6 = 1;
        int[][] expected6 = {{0,1}};
        int[][] result6 = solution.kClosestMaxHeap(points6, k6);

        if (arraysEqual(result6, expected6)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + Arrays.deepToString(expected6));
            System.out.println("Got: " + Arrays.deepToString(result6));
        }
        System.out.println();

        // Test case 7: Large coordinates
        System.out.println("Test 7: Large coordinate values");
        int[][] points7 = {{10000,10000},{1,1},{5000,5000},{100,100}};
        int k7 = 2;
        int[][] expected7 = {{1,1},{100,100}}; // Distances: 2, 20000, 50000000, 200000000
        int[][] result7 = solution.kClosestMaxHeap(points7, k7);

        if (arraysEqualUnordered(result7, expected7)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + Arrays.deepToString(expected7));
            System.out.println("Got: " + Arrays.deepToString(result7));
        }
        System.out.println();

        // Test case 8: Negative coordinates
        System.out.println("Test 8: All negative coordinates");
        int[][] points8 = {{-1,-1},{-3,-4},{-2,-1},{-5,-5}};
        int k8 = 3;
        int[][] expected8 = {{-1,-1},{-2,-1},{-3,-4}}; // Distances: 2, 25, 5, 50
        int[][] result8 = solution.kClosestMaxHeap(points8, k8);

        if (arraysEqualUnordered(result8, expected8)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + Arrays.deepToString(expected8));
            System.out.println("Got: " + Arrays.deepToString(result8));
        }
        System.out.println();

        // Summary
        System.out.println("=== TEST SUMMARY ===");
        System.out.println("Tests passed: " + testsPassed + "/" + totalTests);
        if (testsPassed == totalTests) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("❌ Some tests failed. Check your implementation.");
        }
    }

    // Helper method to check if two 2D arrays are equal (order matters)
    public static boolean arraysEqual(int[][] a, int[][] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (!Arrays.equals(a[i], b[i])) return false;
        }
        return true;
    }

    // Helper method to check if two 2D arrays contain same elements (order doesn't matter)
    public static boolean arraysEqualUnordered(int[][] a, int[][] b) {
        if (a.length != b.length) return false;

        Set<String> setA = new HashSet<>();
        Set<String> setB = new HashSet<>();

        for (int[] point : a) {
            setA.add(Arrays.toString(point));
        }
        for (int[] point : b) {
            setB.add(Arrays.toString(point));
        }

        return setA.equals(setB);
    }

    // Helper method to check if all points in result are from the original array
    public static boolean allPointsValid(int[][] result, int[][] original) {
        Set<String> originalSet = new HashSet<>();
        for (int[] point : original) {
            originalSet.add(Arrays.toString(point));
        }

        for (int[] point : result) {
            if (!originalSet.contains(Arrays.toString(point))) {
                return false;
            }
        }
        return true;
    }
}
