# 3 - Longest Substring Without Repeating Characters

[Problem Link](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)

## Problem Statement

Given a string s, find the length of the longest substring without repeating characters.

## Examples

### Example 1:
```python
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3
```

### Example 2:
```python
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1
```

### Example 3:
```python
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3
Note: Must be substring, not subsequence
```

## Solution Approaches

Both approaches use sliding window technique with two pointers: subStart and subEnd.

### 1. HashSet Approach
1. Initialize:
    - Two pointers: subStart and subEnd at 0
    - HashSet to track current substring chars
    - Variable for max length

2. Slide window:
    - If char not in set:
        - Add to set
        - Update max length
        - Move subEnd forward
    - If char in set:
        - Remove char at subStart
        - Move subStart forward

**Time Complexity:** O(n)
- Each character processed at most twice
- Once for adding, once for removing

**Space Complexity:** O(min(m,n))
- m is size of character set
- n is length of string

### 2. HashMap Approach
1. Initialize:
    - Two pointers: subStart and subEnd at 0
    - HashMap to track char indices
    - Variable for max length

2. Slide window:
    - If char in map:
        - Jump subStart to position after last occurrence
        - Use Math.max to handle cases like "abba"
    - Update char's position in map
    - Update max length
    - Move subEnd forward

**Time Complexity:** O(n)
- Single pass through string
- Each character processed once

**Space Complexity:** O(min(m,n))
- Same as HashSet approach

## Key Differences
- HashSet: Must increment subStart one by one
- HashMap: Can jump subStart directly to right position
- HashMap solution more efficient for strings with many repeats
- HashMap handles edge cases (like "abba") more elegantly

## Implementation Notes
- HashMap needs Math.max when updating subStart
- HashSet solution simpler to understand
- Length calculation differs:
    - HashSet: use set.size()
    - HashMap: use subEnd - subStart + 1