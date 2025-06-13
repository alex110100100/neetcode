# 973 - K Closest Points to Origin

[Problem Link](https://leetcode.com/problems/k-closest-points-to-origin/)

## Problem Statement

Given an array of `points` where `points[i] = [xi, yi]` represents a point on the X-Y plane and an integer `k`, return the `k` closest points to the origin `(0, 0)`.

The distance between two points on the X-Y plane is the Euclidean distance (i.e., `√((x1 - x2)² + (y1 - y2)²)`).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

## Examples

### Example 1:
```
Input: points = [[1,1],[3,3],[2,2]], k = 2
Output: [[1,1],[2,2]]

Distances: [1,1] = √2, [3,3] = √18, [2,2] = √8
Closest 2: [1,1] and [2,2]
```

### Example 2:
```
Input: points = [[3,3],[5,-1],[-2,4]], k = 2  
Output: [[3,3],[-2,4]]
```

## Solution Approaches

### Key Insight
- **Distance calculation**: Use distance² to avoid expensive sqrt operations
- **Two heap strategies**: Min-heap (intuitive) vs Max-heap (efficient)
- **Same pattern as kth largest**: Keep k closest using max-heap of size k

### Approach 1: Min-Heap (Less Efficient)
```java
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
```

**Why less efficient:** Processes all n points even though we only need k

### Approach 2: Max-Heap (More Efficient)
```java
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
```

**Why more efficient:** Maintains heap of size k, discards far points early

## Complexity Analysis

**Time Complexity:**
- **Min-heap approach**: O(n log n) - add all n points, extract k
- **Max-heap approach**: O(n log k) - each point costs O(log k), process n points

**Space Complexity:**
- **Min-heap approach**: O(n) - stores all points
- **Max-heap approach**: O(k) - stores at most k points

## Key Concepts Learned

### Distance Optimization
```java
// Avoid expensive sqrt operation
int distA = a[0] * a[0] + a[1] * a[1];  // distance²
int distB = b[0] * b[0] + b[1] * b[1];  // distance²
// Since sqrt is monotonic, comparing distance² gives same ordering
```

### Max-Heap for "Closest K" Problems
- **Same pattern as kth largest**: Use max-heap to track k best elements
- **Heap invariant**: Contains k closest points seen so far
- **Root = farthest of k closest**: When heap full, compare new point with root

### Custom Comparators for Arrays
```java
// Min-heap: smaller distances first
(a, b) -> Integer.compare(distA, distB)

// Max-heap: larger distances first  
(a, b) -> Integer.compare(distB, distA)

// Alternative max-heap syntax
(a, b) -> distB - distA  // Be careful of integer overflow
```

### Why Max-Heap for Closest Points?
- **Keep k closest**: When we see (k+1)th point, remove farthest of current k
- **Root = farthest**: Top of max-heap is farthest among k closest
- **Efficient rejection**: Compare new point only with farthest of k best


### Min-Heap vs Max-Heap Choice
- **Min-heap**: Natural when you want smallest elements, but processes all data
- **Max-heap**: Counter-intuitive but efficient for "k smallest" by keeping k largest and discarding rest
- **Rule of thumb**: For "k best" problems, use heap of opposite type with size k

**Time Complexity:** O(n log k) max-heap, O(n log n) min-heap  
**Space Complexity:** O(k) max-heap, O(n) min-heap