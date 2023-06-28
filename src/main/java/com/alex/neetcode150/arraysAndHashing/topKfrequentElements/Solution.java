package com.alex.neetcode150.arraysAndHashing.topKfrequentElements;

import java.util.*;

// SOLUTION USING MAX HEAP
class Solution1 {
    public int[] topKFrequent(int[] nums, int k) {
        //Create a HashMap to store the frequency of each element
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }


        // Sort them by frequency, by adding them to a priority queue with a custom Comparator
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> counts.get(b) - counts.get(a)
        );

        for (int num : counts.keySet()) {
            pq.add(num);
        }


        // Take the first K elements from this priority queue and return them as an array
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        return result;
    }
}



// SOLUTION USING MIN HEAP
class Solution2 {
    public int[] topKFrequent(int[] nums, int k) {
        //Create a HashMap to store the frequency of each element
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }


        // Sort them by frequency, by adding them to a priority queue with a custom Comparator
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> counts.get(a) - counts.get(b)
        );

        for (int num : counts.keySet()) {
            pq.add(num);
            if (pq.size() > k) pq.poll();       // keep the heap at a size of k elements, by removing the smallest when the pq size get bigger than k
        }


        // Take the first K elements from this priority queue and return them as an array
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        return result;
    }
}


// SOLUTION USING BUCKETS
class Solution3 {
    public int[] topKFrequent(int[] nums, int k) {
        //Create a HashMap to store the frequency of each element
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        //Create an array of buckets, to house numbers that have the same frequency as each other. The index will represent the frequency
        List<Integer>[] buckets = new ArrayList[nums.length + 1];       // NB: We will have 1 extra bucket due to zero-indexing

        //Put the numbers into the buckets
        for (int num : counts.keySet()) {
            int count = counts.get(num);

            if (buckets[count] == null)
                buckets[count] = new ArrayList<>();
            buckets[count].add(num);
        }

        // Iterate backwards through the buckets, to get k elements with the highest counts         //NB: by "count" I mean count of this element in the original array
        int[] result = new int[k];
        for (int resultIdx = 0, count = buckets.length - 1; count > 0 && resultIdx < k; count--) {
            List<Integer> bucket = buckets[count];
            if (bucket != null) {
                for (int num : buckets[count]) {
                    result[resultIdx++] = num;
                }
            }
        }
        return result;
    }
}




class Test {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();

        // Test Case 1: Basic example
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] expected1 = {1, 2};
        int[] result1 = solution.topKFrequent(nums1, k1);
        System.out.println(containsAllElements(result1, expected1) ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2: Edge case with k = 0
        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 0;
        int[] expected2 = {};
        int[] result2 = solution.topKFrequent(nums2, k2);
        System.out.println(containsAllElements(result2, expected2) ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3: Edge case with duplicate elements and k = 1
        int[] nums3 = {4, 2, 1, 2, 1, 3, 3, 1};
        int k3 = 1;
        int[] expected3 = {1};
        int[] result3 = solution.topKFrequent(nums3, k3);
        System.out.println(containsAllElements(result3, expected3) ? "Test Case 3 Passed" : "Test Case 3 Failed");
    }

    private static boolean containsAllElements(int[] arr, int[] elements) {
        if (arr.length != elements.length) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        for (int element : elements) {
            if (!set.contains(element)) {
                return false;
            }
        }

        return true;
    }
}

