# 347 - Top K Frequent Elements

[Problem Link](https://leetcode.com/problems/top-k-frequent-elements/description/)

## Problem Statement

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

## Examples

### Example 1:
```python
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```

### Example 2:
```python
Input: nums = [1], k = 1
Output: [1]
```

## Common First Step

Before applying any of the solutions below, we need to count frequencies:
- Create a HashMap with numbers as keys and their frequencies as values
- Iterate through the array once to populate the HashMap
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)

## Solution Approaches

> Note: Priority Queue operations (add/poll) take O(log n) time

### 1. Max Heap Approach
- Create a MAX heap Priority Queue with custom comparator:
  ```java
  (a, b) -> counts.get(b) - counts.get(a)
  ```
- Add all HashMap keys to the PQ
- Poll first k elements


- **Time Complexity:** O(n log n)
    - Building heap with n elements: O(n log n)
    - Polling k elements: O(k log n)
- **Space Complexity:** O(n)
    - Storing all n elements in heap

### 2. Min Heap Approach
- Create a MIN heap Priority Queue with custom comparator:
  ```java
  (a, b) -> counts.get(a) - counts.get(b)
  ```
- Add elements while maintaining heap size ≤ k
- Poll remaining elements for result


- **Time Complexity:** O(n log k)
    - Processing n elements with heap size k: O(n log k)
- **Space Complexity:** O(k)
    - Only storing k elements in heap

### 3. Bucket Sort Approach
- Create array of integer lists ("buckets")
    - Index represents frequency
    - List at index contains numbers with that frequency
    - Size needs to be `nums.length + 1` (due to zero-indexing)
- Example:
    - For array [1,2,2,3,3,4,4]
    - Bucket[2] = [2,3,4] means these numbers each appeared twice
- Iterate backwards through k buckets to get result


- **Time Complexity:** O(n)
    - Single pass to create frequency map
    - Single pass to create buckets
    - Single pass to collect results
- **Space Complexity:** O(n)
    - Storing all numbers in buckets

## Implementation Notes
- Bucket sort approach is most efficient (O(n))
- Min heap approach is more memory efficient than max heap
- Max heap is simplest to implement but least efficient of these 3 approaches