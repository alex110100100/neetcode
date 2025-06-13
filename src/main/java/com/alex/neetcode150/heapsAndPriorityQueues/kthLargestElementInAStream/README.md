# 703 - Kth Largest Element in a Stream

[Problem Link](https://leetcode.com/problems/kth-largest-element-in-a-stream/)

## Problem Statement

Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Implement `KthLargest` class:
- `KthLargest(int k, int[] nums)` Initializes the object with the integer k and the stream of integers nums.
- `int add(int val)` Appends the integer val to the stream and returns the element representing the kth largest element in the stream.

## Examples

### Example 1:
```
Input: ["KthLargest", "add", "add", "add", "add", "add"]
       [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]

Output: [null, 4, 5, 5, 8, 8]

Explanation:
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4 (3rd largest: [8,5,4])
kthLargest.add(5);   // return 5 (3rd largest: [8,5,5])
kthLargest.add(10);  // return 5 (3rd largest: [10,8,5])
kthLargest.add(9);   // return 8 (3rd largest: [10,9,8])
kthLargest.add(4);   // return 8 (3rd largest: [10,9,8])
```

## Solution Approach

### Key Insight
- **Min-heap of size k**: Keep only the k largest elements seen so far
- **Heap property**: Root of min-heap = smallest of the k largest = kth largest overall
- **Stream processing**: Add new elements, maintain heap size k

### Main Solution: Min-Heap
```java
class KthLargest {
    PriorityQueue<Integer> minHeap;
    int maxSize;

    public KthLargest(int k, int[] nums) {
        minHeap = new PriorityQueue<>();
        maxSize = k;
        
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        
        if (minHeap.size() > maxSize)
            minHeap.remove();
        
        return minHeap.peek();
    }
}
```

**Why this works:**
- **Min-heap invariant**: Always contains k largest elements
- **Root = kth largest**: Smallest element in heap of k largest elements
- **Size control**: Remove smallest when heap exceeds size k
- **Efficient updates**: O(log k) per addition

## Algorithm Walkthrough

### Initialization with [4, 5, 8, 2], k=3:
```
add(4): heap = [4]
add(5): heap = [4, 5]  
add(8): heap = [4, 5, 8]
add(2): heap = [4, 5, 8], remove(2) → heap = [4, 5, 8]
```

### Stream additions:
```
add(3): heap = [4, 5, 8] → [3, 5, 8] → remove(3) → [5, 8] → return 4
add(5): heap = [4, 5, 8] → [4, 5, 5, 8] → remove(4) → [5, 5, 8] → return 5
```

## Complexity Analysis

**Time Complexity:**
- **Constructor**: O(n log k) where n = length of nums array
- **add()**: O(log k) per call
- **Overall**: Efficient for stream processing

**Space Complexity:** O(k)
- Heap stores at most k elements
- Memory usage independent of stream length

## Key Concepts Learned

### Min-Heap for "Largest" Problems
- **Counter-intuitive**: Use min-heap to find largest elements
- **Why it works**: Keep k largest, remove smallest of those k
- **Heap root**: Always the kth largest (smallest of k largest)

### PriorityQueue in Java
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();  // Min-heap by default
minHeap.offer(val);     // Insert element
minHeap.remove();     // Remove root (smallest)
minHeap.peek();       // Get root without removing
```

### Stream vs Batch Processing
- **Stream**: Process elements one at a time as they arrive
- **Advantage**: Constant space usage, don't need to store all elements
- **Real-world**: Network data, sensor readings, user interactions

### Size-Limited Data Structures
- **Pattern**: Maintain fixed-size collection of "best" elements
- **Applications**: Top-k problems, sliding window max/min
- **Implementation**: Add new element, remove excess if over limit


### Heap Size Optimization
- **Always maintain exactly k elements** (except when fewer than k total)
- **Never let heap grow beyond k+1** temporarily
- **Memory efficient**: O(k) space regardless of stream length

**Time Complexity:** O(log k) per add, O(n log k) constructor  
**Space Complexity:** O(k)