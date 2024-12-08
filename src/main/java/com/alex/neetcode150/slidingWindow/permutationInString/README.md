# 567 - Permutation in String

[Problem Link](https://leetcode.com/problems/permutation-in-string/description/)

## Problem Statement

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
In other words, return true if one of s1's permutations is the substring of s2.

### Constraints
- 1 <= s1.length, s2.length <= 10^4
- s1 and s2 consist of lowercase English letters

## Examples

### Example 1:
```python
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains permutation "ba"
```

### Example 2:
```python
Input: s1 = "ab", s2 = "eidboaoo"
Output: false
```

## Solution Approach

### Key Insights
- A permutation has the same character frequencies as the original string
- Can use sliding window of size s1.length() to check substrings
- Only need to compare character counts, not actual characters

### Implementation Strategy
1. Early exit check:
    - Return false if s1 longer than s2

2. Initialize character count arrays:
    - One for s1
    - One for current window in s2
    - Size 26 for lowercase letters

3. Sliding Window:
    - Initial window: first s1.length() characters of s2
    - Slide window through s2
    - Update window counts as window moves
    - Check for match at each position

4. Match Checking:
    - Compare frequency arrays
    - Return true if all frequencies match

**Time Complexity:** O(n)
- n is length of s2
- Array comparison is O(1) (always size 26)

**Space Complexity:** O(1)
- Fixed size arrays (26) regardless of input

## Implementation Notes
- Use 'char - 'a'' to map characters to array indices
- Need final check after loop (for last window)
- Separate match checking function improves readability
- No need to generate actual permutations
- Window size remains fixed throughout