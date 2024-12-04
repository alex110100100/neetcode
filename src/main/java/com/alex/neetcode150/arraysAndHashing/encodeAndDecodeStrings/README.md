# Encode and Decode Strings

[Problem Link](https://neetcode.io/problems/string-encode-and-decode)

## Problem Statement

Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Please implement encode and decode.

## Examples

### Example 1:
```python
Input: ["lint","code","love","you"]
Output: ["lint","code","love","you"]
Explanation: One possible encode method is: "lint:;code:;love:;you"
```

### Example 2:
```python
Input: ["we", "say", ":", "yes"]
Output: ["we", "say", ":", "yes"]
Explanation: One possible encode method is: "we:;say:;:::;yes"
```

## Solution Approach

### Key Considerations
- Need to convert list of strings into a single string
- Need a delimiter to separate strings
- Must handle case where delimiter character appears in original strings

### Implementation Strategy
- Use '#' as delimiter (could be any character)
- Before each string, include its length followed by '#'
- This way, when decoding:
    - We know exactly how many characters to read
    - We don't accidentally split on '#' characters that were part of original strings

### Example Encoding
```
Input: ["Hello", "World"]
Encoded: "5#Hello5#World"
        |     |    |     |
        |     |    |     actual string "World"
        |     |    delimiter
        |     actual string "Hello"
        length marker "5" followed by delimiter
```

## Why This Works
- Length prefix solves the delimiter ambiguity problem
- During decoding, we can:
    1. Read until we find '#'
    2. Parse that number to get string length
    3. Read exact number of characters for next string
    4. Repeat until end of encoded string

**Time Complexity:** O(n)
- Where n is total length of all strings

**Space Complexity:** O(n)
- Need to store the encoded/decoded strings