# 11 - Container With Most Water

[Problem Link](https://leetcode.com/problems/container-with-most-water/description/)

## Problem Statement

You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]). Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Note: You may not slant the container.

## Examples

### Example 1:
```python
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: Container between heights 8 and 7 (indices 1 and 8)
Width = 7, Height = min(8,7) = 7
Area = 7 * 7 = 49
```

### Example 2:
```python
Input: height = [1,1]
Output: 1
```

## Solution Approach

### Key Insights
- Area is determined by:
    - Width (distance between lines)
    - Height (minimum of two line heights)
- At any point, the shorter line is the limiting factor
- Moving the taller pointer inward can only decrease area

### Two-Pointer Strategy
1. Start with widest possible container:
    - Left pointer at start
    - Right pointer at end

2. On each iteration:
    - Calculate current area
    - Move the pointer with smaller height inward
    - Reasoning: Current position already has maximum possible area for shorter line

### Area Calculation
```
area = width * height
     = (right - left) * min(height[left], height[right])
```

**Time Complexity:** O(n)
- Single pass through array
- Each element visited at most once

**Space Complexity:** O(1)
- Only using two pointers
- No extra space needed

## Implementation Notes
- Similar to Two Sum II (sorted array) approach
- Always move smaller height pointer
- Track maximum area seen so far
- No need to consider all possible combinations