# 1046 - Last Stone Weight

[Problem Link](https://leetcode.com/problems/last-stone-weight/)

## Problem Statement

You are given an array of integers `stones` where `stones[i]` is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights `x` and `y` with `x <= y`. The result of this smash is:

- If `x == y`, both stones are destroyed, and
- If `x != y`, the stone of weight `x` is destroyed, and the stone of weight `y` has new weight `y - x`.

At the end of the game, there is at most one stone left.

Return the weight of the last stone. If there are no stones left, return 0.

## Examples

### Example 1:
```
Input: stones = [2,7,4,1,8,1]
Output: 1

Process:
[2,7,4,1,8,1] → take 8,7 → [2,4,1,1,1] (8-7=1)
[2,4,1,1,1] → take 4,2 → [1,1,1,2] (4-2=2)  
[1,1,1,2] → take 2,1 → [1,1,1] (2-1=1)
[1,1,1] → take 1,1 → [1] (1-1=0, both destroyed)
Result: 1
```

### Example 2:
```
Input: stones = [1]
Output: 1
```

## Solution Approach

### Key Insight
- **Always need heaviest two stones**: Perfect use case for max-heap
- **Simulation**: Process stones according to game rules until ≤1 stone remains
- **Heap operations**: Extract max twice, potentially insert difference

### Main Solution: Max-Heap Simulation
```java
public int lastStoneWeight(int[] stones) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    
    for (int stone : stones)
        maxHeap.offer(stone);
    
    while (maxHeap.size() > 1) {
        int stone1 = maxHeap.poll();  // Heaviest
        int stone2 = maxHeap.poll();  // Second heaviest
        
        if (stone1 != stone2)
            maxHeap.offer(Math.abs(stone1 - stone2));
    }
    
    return maxHeap.isEmpty() ? 0 : maxHeap.poll();
}
```

**Why this works:**
- **Max-heap**: Always gives us the two heaviest stones
- **Game simulation**: Follow exact rules - smash two heaviest, add remainder if different
- **Termination**: Continue until 0 or 1 stones remain

## Algorithm Walkthrough

### Example: [2,7,4,1,8,1]
```
Initial heap: [8,7,4,2,1,1]
Round 1: poll(8), poll(7) → stone1=8, stone2=7 → offer(1) → heap=[4,2,1,1,1]
Round 2: poll(4), poll(2) → stone1=4, stone2=2 → offer(2) → heap=[2,1,1,1]  
Round 3: poll(2), poll(1) → stone1=2, stone2=1 → offer(1) → heap=[1,1,1]
Round 4: poll(1), poll(1) → stone1=1, stone2=1 → no offer → heap=[1]
Result: heap.poll() = 1
```

## Complexity Analysis

**Time Complexity:** O(n log n)
- Initial heap construction: O(n log n)
- Each round: O(log n) for two polls + potential offer
- At most n rounds (each round removes at least 1 stone)
- Total: O(n log n)

**Space Complexity:** O(n)
- Heap stores all stones initially
- Heap size decreases over time but starts at O(n)

## Key Concepts Learned

### Max-Heap in Java
```java
// Default PriorityQueue is min-heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max-heap using Collections.reverseOrder()
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// Alternative: Custom comparator
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
```

### Simulation Pattern
- **Game rules**: Translate problem rules directly into code
- **Loop until termination**: Continue while game can proceed
- **State changes**: Update data structures after each action

### Heap for "Best Two" Problems
- **Pattern**: When you need the largest/smallest two elements repeatedly
- **Efficiency**: O(log n) to get and update vs O(n log n) to sort each time
- **Examples**: Stone weight, merge intervals, task scheduling

### Conditional Insertion
```java
if (stone1 != stone2)
    maxHeap.offer(Math.abs(stone1 - stone2));
```
- **Don't always insert**: Only add result if stones are different weights
- **Math.abs()**: Safety measure, though stone1 ≥ stone2 by heap property

## Implementation Notes

### Edge Cases
- Single stone: return that stone's weight
- All stones same weight: pairs cancel out, result depends on count (even=0, odd=weight)
- Empty array: return 0

### Alternative Approaches
```java
// Less efficient: Sort array each round
// Time: O(n² log n)
while (stones.size() > 1) {
    Arrays.sort(stones);
    // Process largest two...
}
```

### Why Max-Heap Not Min-Heap?
- **Need largest elements**: Game rules specify "heaviest two stones"
- **Min-heap**: Would give smallest stones, wrong for this problem
- **Contrast**: With kth largest problem, we used min-heap differently

### Return Value Logic
```java
return maxHeap.isEmpty() ? 0 : maxHeap.poll();
```
- **Empty heap**: All stones destroyed in pairs
- **One stone**: Last remaining stone weight

**Time Complexity:** O(n log n)  
**Space Complexity:** O(n)