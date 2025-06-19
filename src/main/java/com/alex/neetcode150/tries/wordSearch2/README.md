# 212 - Word Search II

[Problem Link](https://leetcode.com/problems/word-search-ii/)

## Problem Statement

Given an `m x n` board of characters and a list of strings `words`, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

## Examples

### Example 1:
```
Input: board = [["o","a","a","n"],
                ["e","t","a","e"],
                ["i","h","k","r"],
                ["i","f","l","v"]], 
       words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
```

### Example 2:
```
Input: board = [["a","b"],
                ["c","d"]], 
       words = ["abcb"]
Output: []
```

## Solution Approach

### Key Insight
- **Trie + DFS combination**: Use Trie to efficiently search multiple words simultaneously
- **Backtracking on board**: Explore all paths from each cell while avoiding revisits
- **Optimization**: Store complete word in TrieNode to avoid reconstruction

### Modified TrieNode Design
```java
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    String word;  // Store complete word instead of boolean flag
}
```

**Why store the word:** Avoids need to reconstruct word during DFS traversal

### Main Solution: Trie + Backtracking
```java
public List<String> findWords(char[][] board, String[] words) {
    TrieNode root = buildTrie(words);
    List<String> result = new ArrayList<>();
    
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            dfs(board, i, j, root, result);
        }
    }
    
    return result;
}

private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
    // Boundary checks
    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
    
    char c = board[i][j];
    // Check if cell already visited or character not in trie
    if (c == '#' || !node.children.containsKey(c)) return;
    
    // Move to next trie node
    node = node.children.get(c);
    
    // Found complete word
    if (node.word != null) {
        result.add(node.word);
        node.word = null;  // Avoid duplicates
    }
    
    // Mark cell as visited
    board[i][j] = '#';
    
    // Explore all 4 directions
    dfs(board, i + 1, j, node, result);
    dfs(board, i - 1, j, node, result);
    dfs(board, i, j + 1, node, result);
    dfs(board, i, j - 1, node, result);
    
    // Backtrack: restore original character
    board[i][j] = c;
}

private TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();
    
    for (String word : words) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }
        curr.word = word;  // Store complete word at end node
    }
    
    return root;
}
```

## Algorithm Walkthrough

### Board Setup and Trie Building
```
Board:           Trie (for words ["eat", "oath"]):
o a a n                    root
e t a e                   /    \
i h k r                  e      o
i f l v                  |      |
                         a      a
                         |      |
                         t      t
                      (word=    |
                       "eat")   h
                               (word=
                                "oath")
```

### DFS from board[1][0] = 'e':
```
dfs(board, 1, 0, root, result):
  c = 'e', node = root.children['e'] ✓
  node.word = null (not a complete word yet)
  board[1][0] = '#'
  
  dfs(board, 2, 0, node, result): // down to 'i'
    c = 'i', node.children['i'] doesn't exist ✗
    return
  
  dfs(board, 0, 0, node, result): // up to 'o'  
    c = 'o', node.children['o'] doesn't exist ✗
    return
  
  dfs(board, 1, 1, node, result): // right to 't'
    c = 't', node.children['t'] doesn't exist ✗
    return
  
  dfs(board, 1, -1, node, result): // left (out of bounds)
    return
  
  board[1][0] = 'e' // backtrack

... continue from other starting positions until "eat" and "oath" found
```

## Complexity Analysis

**Time Complexity:** O(M × N × 4^L)
- M × N = board dimensions
- 4^L = worst case DFS exploration (4 directions, L = max word length)
- Trie optimization reduces constant factors significantly

**Space Complexity:** O(K × L)
- K = number of words
- L = average word length
- Space for trie storage

## Key Concepts Learned

### Trie vs Naive Approach
```java
// Naive: For each word, search entire board
for (String word : words) {
    if (existsOnBoard(board, word)) result.add(word);
}
// Time: O(W × M × N × 4^L) where W = number of words

// Trie approach: Search all words simultaneously
// Time: O(M × N × 4^L) - much better when W is large
```

### Board Modification for Visited Tracking
```java
// Mark visited
board[i][j] = '#';

// Backtrack (restore original)
board[i][j] = c;
```

**Why this works:**
- '#' is not a valid letter, so DFS won't continue on visited cells
- Avoids need for separate boolean[][] visited array
- Automatic cleanup through backtracking

### Duplicate Prevention
```java
if (node.word != null) {
    result.add(node.word);
    node.word = null;  // Set to null to prevent re-adding
}
```

**Why necessary:** Same word might be found via different paths on board

### Why Store Word in TrieNode
```java
// Alternative: Reconstruct word during DFS
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
}
// Would need to pass StringBuilder and build word character by character

// Your approach: Store complete word
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    String word;  // Much simpler!
}
```

## Implementation Notes

### 4-Direction DFS Pattern
```java
// Standard pattern for grid exploration
int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
for (int[] dir : directions) {
    dfs(board, i + dir[0], j + dir[1], node, result);
}

// Your approach (more explicit)
dfs(board, i + 1, j, node, result);
dfs(board, i - 1, j, node, result);
dfs(board, i, j + 1, node, result);
dfs(board, i, j - 1, node, result);
```

### Optimization Opportunities
```java
// Further optimization: Remove empty branches from trie
private void removeBranch(TrieNode node, char c) {
    if (node.children.get(c).children.isEmpty()) {
        node.children.remove(c);
    }
}
```

### Edge Cases
- Empty board or words array
- Words longer than possible on board
- Single character words
- All words found vs none found
- Board with repeated characters

### Real-World Applications
- **Word games**: Boggle, Word Search puzzles
- **Autocomplete**: Finding words with spatial constraints
- **Pattern matching**: 2D text processing
- **Game AI**: Finding optimal word placements

**Time Complexity:** O(M × N × 4^L)  
**Space Complexity:** O(K × L)