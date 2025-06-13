# 215 - Kth Largest Element in an Array

[Problem Link](https://leetcode.com/problems/kth-largest-element-in-an-array/)

## Problem Statement

Given an integer array `nums` and an integer `k`, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?

## Examples

### Example 1:
```
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Explanation: Sorted array: [6,5,4,3,2,1], 2nd largest = 5
```

### Example 2:
```
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
Explanation: Sorted array: [6,5,5,4,3,3,2,2,1], 4th largest = 4
```

## Solution Approach

### Key Insight
- **Same pattern as stream version**: Use min-heap of size k to track k largest elements
- **Heap root = kth largest**: Smallest element in heap of k largest elements
- **One-pass solution**: Process array once, maintain heap invariant

### Main Solution: Min-Heap of Size K
```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    
    for (int num : nums) {
        minHeap.offer(num);
        
        if (minHeap.size() > k)
            minHeap.poll();
    }
    
    return minHeap.poll();
}
```

**Why this works:**
- **Heap invariant**: Always contains k largest elements seen so far
- **Size control**: Remove smallest when heap exceeds size k
- **Root = answer**: Smallest of k largest = kth largest overall

## Algorithm Walkthrough

### Example: nums = [3,2,1,5,6,4], k = 2
```
Process 3: heap = [3]
Process 2: heap = [2,3]  
Process 1: heap = [1,2,3] → size > 2 → poll(1) → heap = [2,3]
Process 5: heap = [2,3,5] → size > 2 → poll(2) → heap = [3,5]
Process 6: heap = [3,5,6] → size > 2 → poll(3) → heap = [5,6]
Process 4: heap = [4,5,6] → size > 2 → poll(4) → heap = [5,6]
Result: poll() = 5 (2nd largest)
```

## Alternative Approaches

### Approach 2: Sorting (Simple but Less Efficient)
```java
public int findKthLargest(int[] nums, int k) {
    Arrays.sort(nums);
    return nums[nums.length - k];  // kth largest from end
}
```

### Approach 3: QuickSelect (Optimal but Complex)
```java
// O(n) average case using partition logic from quicksort
// More complex implementation, not covered here
```

## Complexity Analysis

**Time Complexity:**
- **Min-heap approach**: O(n log k)
    - Process n elements, each heap operation costs O(log k)
- **Sorting approach**: O(n log n)
- **QuickSelect**: O(n) average, O(n²) worst case

**Space Complexity:**
- **Min-heap approach**: O(k)
- **Sorting approach**: O(1) or O(n) depending on sort algorithm
- **QuickSelect**: O(1)

## Key Concepts Learned

### Stream vs Array Versions
- **Problem 703 (Stream)**: Multiple add() calls, need persistent data structure
- **Problem 215 (Array)**: Single query, can process array once
- **Same core algorithm**: Min-heap of size k in both cases

### Min-Heap for "Largest" Pattern
```java
// Counter-intuitive but efficient pattern
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
// Keep k largest elements, root = kth largest
```

### Why Not Max-Heap?
```java
// Max-heap approach would be less efficient
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
// Would need to store all elements, then extract k times
// Time: O(n log n), Space: O(n)
```

### Heap Size Management
- **Pattern**: Add element first, then check size and remove excess
- **Invariant**: Heap size never exceeds k+1 temporarily
- **Efficiency**: Only store k elements regardless of input size



### When to Use Each Approach
- **Min-heap**: When k is small relative to n, or need online processing
- **Sorting**: When implementation simplicity matters and n is small
- **QuickSelect**: When optimal time complexity is critical and space is limited

**Time Complexity:** O(n log k)  
**Space Complexity:** O(k)