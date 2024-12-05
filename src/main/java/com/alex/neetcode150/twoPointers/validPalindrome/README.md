# 125 - Valid Palindrome

[Problem Link](https://leetcode.com/problems/valid-palindrome/description/)

## Problem Statement

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

## Examples

### Example 1:
```python
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome
```

### Example 2:
```python
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome
```

### Example 3:
```python
Input: s = " "
Output: true
Explanation: Empty string is a palindrome
```

## Solution Approach

### Two-Pointer Strategy
Use two pointers that move toward each other from opposite ends:
- Left pointer starts at beginning
- Right pointer starts at end
- Move inward until pointers meet

### Implementation Details
1. Initialize pointers:
    - i at start (0)
    - j at end (length - 1)

2. While i < j:
    - Skip non-alphanumeric chars from left
    - Skip non-alphanumeric chars from right
    - Compare characters (case-insensitive)
    - Move pointers inward

### Java-Specific Notes
- Use `Character.isLetterOrDigit()` to check for alphanumeric chars
- Use `Character.toLowerCase()` for case-insensitive comparison
- Use `continue` to skip non-alphanumeric characters efficiently

**Time Complexity:** O(n)
- Single pass through the string
- Each character is examined at most once

**Space Complexity:** O(1)
- Only using two pointers
- No extra space needed for string manipulation

## Implementation Notes
- No need to clean string first (saves space)
- No need for additional data structures
- Handles empty strings and single characters automatically
- Continues past non-alphanumeric characters without breaking flow