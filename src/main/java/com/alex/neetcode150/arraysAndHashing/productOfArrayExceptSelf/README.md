# 238 - Product of Array Except Self

[Problem Link](https://leetcode.com/problems/product-of-array-except-self/description/)

## Problem Statement

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

### Constraints
- The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer
- Must run in O(n) time
- Cannot use division operation

## Examples

### Example 1:
```python
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
```

### Example 2:
```python
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
```

## Solution Approaches

The constraints make this problem tricky. Without them, you could:
- Multiply all elements together and divide by each element (but division isn't allowed)
- For each element, multiply all other elements (but this would be O(n²))

### Key Insight
For each position i, the result is:
- (product of all elements to the left) × (product of all elements to the right)

### Solution 1: Using Two Arrays
Using input array `[2,1,3,4]` as example:

1. Create leftProducts array:
    - leftProducts[i] = product of elements from nums[0] to nums[i-1]
    - Initialize leftProducts[0] = 1 (no elements to left of first position)
    - Result: `[1,2,2,6]`

2. Create rightProducts array:
    - rightProducts[i] = product of elements from nums[i+1] to nums[end]
    - Initialize rightProducts[last] = 1 (no elements to right of last position)
    - Result: `[12,12,4,1]`

3. Final result is product of corresponding elements:
    - result[i] = leftProducts[i] × rightProducts[i]

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

### Solution 2: Space Optimized
Same concept but optimized:
- Only create one array (leftProducts)
- Calculate right products on the fly while iterating backwards
- Multiply with leftProducts immediately
- Use this array as the result

**Time Complexity:** O(n)  
**Space Complexity:** O(1)  
(excluding the required output array)

## Implementation Notes
- Solution 2 is preferred as it uses constant extra space
- Both solutions meet the O(n) time requirement
- Neither solution uses division