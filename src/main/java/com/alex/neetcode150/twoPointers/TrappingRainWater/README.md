# 42 - Trapping Rain Water

[Problem Link](https://leetcode.com/problems/trapping-rain-water/description/)

## Problem Statement

You are given an array of non-negative integers height which represents an elevation map. Each value height[i] represents the height of a bar of width 1.

Return the maximum area of water that can be trapped between the bars.

### Constraints
- 1 <= height.length <= 1000
- 0 <= height[i] <= 1000

## Examples

### Example 1:
```python
Input: height = [0,2,0,3,1,0,1,3,2,1]
Output: 9
```

## Key Insight

Water in each column is determined by:
- Highest wall anywhere to its left
- Highest wall anywhere to its right
- Height of current column
- Formula: min(maxLeftHeight, maxRightHeight) - currentHeight

## Solution Approaches

### 1. Array-Based Solution
1. Create arrays to store maximum heights:
   - maxLeftHeights: max height to left of each index
   - maxRightHeights: max height to right of each index

2. Fill arrays:
   - Iterate left to right for maxLeftHeights
   - Iterate right to left for maxRightHeights

3. Calculate water for each column:
   - Use formula: min(maxLeftHeight, maxRightHeight) - currentHeight
   - Sum positive values

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

### 2. Two-Pointer Solution
1. Initialize:
   - Left pointer at start
   - Right pointer at end
   - Track maxLeftHeight and maxRightHeight

2. Move pointers inward:
   - Compare maxLeftHeight and maxRightHeight
   - Move left pointer inward if maxLeftHeight < maxRightHeight   (or right pointer otherwise)
   - Update max heights as we go
   - Calculate water for each column

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

## Implementation Notes

### Array Solution
- Easier to understand conceptually
- Requires extra space for height arrays
- Good first approach to problem

### Two-Pointer Solution
- More space efficient
- Same logic but tracks max heights on the fly
- Move pointer on side with smaller max height because:
   - Smaller height is limiting factor
   - Other side can't affect result until we find higher wall

## Optimization
Two-pointer approach optimizes the array solution by:
1. Eliminating need for extra arrays
2. Processing each position only once
3. Calculating water trapped as we go