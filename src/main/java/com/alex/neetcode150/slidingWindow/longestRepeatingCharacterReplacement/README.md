# 424 - Longest Repeating Character Replacement

[Problem Link](https://leetcode.com/problems/longest-repeating-character-replacement/description/)

## Problem Statement

You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

## Examples

### Example 1:
```python
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace both 'A's with 'B's or vice versa
```

### Example 2:
```python
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace middle 'A' with 'B' to get "AABBBBA"
Substring "BBBB" has longest repeating letters
```

## Solution Approach

### Key Insights
- Window is valid if: window_length - count_of_most_frequent_char ≤ k
- This represents the number of characters we need to replace
- When window becomes invalid, shrink from left

### Sliding Window Strategy
1. Initialize:
    - Window pointers (start, end)
    - Array to count characters
    - Variable to track most frequent char count

2. For each position:
    - Expand window (increment end)
    - Update character count
    - Update most frequent character count
    - If window invalid (more than k replacements needed):
        - Shrink window from left
        - Update character count

### Window Validity Check
```
windowLength - maxCharCount <= k
where:
- windowLength = end - start + 1
- maxCharCount = most frequent char in current window
- k = allowed replacements
```

**Time Complexity:** O(n)
- Single pass through string
- Character count array operations are O(1)

**Space Complexity:** O(1)
- Fixed size array for character counts (26 for uppercase letters)

## Implementation Notes
- Use int[26] for character counts (uppercase letters)
- Window only shrinks when invalid
- Final answer is length of longest valid window seen