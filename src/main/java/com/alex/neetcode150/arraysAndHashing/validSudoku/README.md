# 36 - Valid Sudoku

[Problem Link](https://leetcode.com/problems/valid-sudoku/description/)

## Problem Statement

Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
1. Each row must contain the digits 1-9 without repetition
2. Each column must contain the digits 1-9 without repetition
3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition

### Important Notes
- A Sudoku board (partially filled) could be valid but is not necessarily solvable
- Only the filled cells need to be validated
- Empty cells are represented by '.'

### Constraints
- board.length == 9
- board[i].length == 9
- board[i][j] is a digit 1-9 or '.'

## Examples

### Example 1:
```
Input: board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]

Output: true
```

### Example 2:
```
Input: board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]

Output: false

Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. 
Since there are two 8's in the top left 3x3 sub-box, it is invalid.
```

## Solution Approach

### Key Data Structures
- Array of HashSets for columns: `Set<Character>[] columnSets`
- Array of HashSets for rows: `Set<Character>[] rowSets`
- 2D array of HashSets for 3x3 boxes: `Set<Character>[][] boxSets`

### Key Insights
- For 3x3 boxes, can use integer division to map coordinates:
    - Row index ÷ 3 gives box row
    - Column index ÷ 3 gives box column
    - Example: cell (7,4) maps to box (2,1)

### Implementation Details
1. Initialize arrays of sets for rows, columns, and boxes
2. Iterate through board once
3. For each non-empty cell:
    - Try to add number to corresponding row set
    - Try to add number to corresponding column set
    - Try to add number to corresponding box set
4. If any add() returns false, we found a duplicate

**Time Complexity:** O(1)
- We always process exactly 81 cells
- HashSet operations are O(1)

**Space Complexity:** O(1)
- Fixed size data structures:
    - 9 sets for rows
    - 9 sets for columns
    - 9 sets for boxes (3x3 array)
- Maximum 81 elements total

## Implementation Notes
- Lazy initialization of sets saves a bit of space
- Using HashSet's add() return value avoids need for separate contains() check
- Single pass through board is sufficient


NB: You could also use a bitmask approach instead of HashSets, to save space