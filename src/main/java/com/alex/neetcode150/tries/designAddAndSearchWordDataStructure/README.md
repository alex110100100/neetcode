# 211 - Design Add and Search Words Data Structure

[Problem Link](https://leetcode.com/problems/design-add-and-search-words-data-structure/)

## Problem Statement

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the `WordDictionary` class:
- `WordDictionary()` Initializes the object.
- `void addWord(word)` Adds word to the data structure, it can be matched later.
- `bool search(word)` Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.

## Examples

### Example 1:
```
Input: ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
       [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output: [null,null,null,null,false,true,true,true]

Explanation:
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True (matches "bad", "dad", "mad")
wordDictionary.search("b.."); // return True (matches "bad")
```

## Solution Approaches

### Key Challenge
- **Wildcard matching**: The '.' character can match any letter
- **Need backtracking**: When encountering '.', must try all possible children
- **Trie + DFS**: Perfect combination for prefix matching with wildcards

### Approach 1: Create New Objects for Recursion
```java
class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean endOfWord = false;
}

class WordDictionary1 {
    private TrieNode root;

    public WordDictionary1() {
        root = new TrieNode();
    }

    public WordDictionary1(TrieNode root) {
        this.root = root;
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }
        curr.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);

            if (c == '.') {
                // Try all possible children for wildcard
                for (TrieNode node : curr.children.values()) {
                    WordDictionary1 wd = new WordDictionary1(node);
                    
                    if (wd.search(word.substring(i + 1)))
                        return true;
                }
                return false;
            }

            // Regular character matching
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.endOfWord;
    }
}
```

**Pros:** Intuitive reuse of main search method  
**Cons:** Creates new objects and substrings on each recursion (memory inefficient)

### Approach 2: Helper Method with Index (Optimal)
```java
class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }
        curr.endOfWord = true;
    }

    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }

    public boolean searchHelper(String word, TrieNode node, int index) {
        // Base case: reached end of word
        if (index == word.length())
            return node.endOfWord;

        Character c = word.charAt(index);

        if (c == '.') {
            // Try all possible children for wildcard
            for (TrieNode child : node.children.values()) {
                if (searchHelper(word, child, index + 1))
                    return true;
            }
            return false;
        }

        // Regular character matching
        if (!node.children.containsKey(c))
            return false;

        node = node.children.get(c);
        return searchHelper(word, node, index + 1);
    }
}
```

**Why this is better:** No extra object creation, no substring creation, more efficient memory usage

## Algorithm Walkthrough

### Example: After adding ["bad", "dad", "mad"], search(".ad")

**Trie structure:**
```
        root
       / | \
      b  d  m
      |  |  |
      a  a  a
      |  |  |
      d  d  d (all marked endOfWord=true)
```

**Search ".ad" walkthrough:**
```
searchHelper(".ad", root, 0):
  index=0, c='.'
  Try all children of root: [b-node, d-node, m-node]
  
  searchHelper(".ad", b-node, 1):
    index=1, c='a'
    b-node has child 'a', move to ba-node
    searchHelper(".ad", ba-node, 2):
      index=2, c='d'  
      ba-node has child 'd', move to bad-node
      searchHelper(".ad", bad-node, 3):
        index=3 == word.length(), return bad-node.endOfWord = true ✓
      
  (Found match, return true without trying other branches)
```

## Complexity Analysis

**Time Complexity:**
- **addWord**: O(m) where m = word length
- **search**:
    - Best case (no dots): O(m)
    - Worst case (all dots): O(n × 26^m) where n = number of nodes, 26 = alphabet size

**Space Complexity:**
- **Storage**: O(ALPHABET_SIZE × N × M) for trie
- **Recursion**: O(m) for search depth

## Key Concepts Learned

### Trie + DFS Pattern
- **Trie structure**: Efficient prefix storage and retrieval
- **DFS exploration**: When encountering wildcards, explore all possible paths
- **Backtracking nature**: Try each possibility, backtrack if no match

### Recursion Design Choices
```java
// Approach 1: Reuse main method with new objects
WordDictionary wd = new WordDictionary(node);
wd.search(word.substring(i + 1));

// Approach 2: Helper method with parameters
searchHelper(word, node, index + 1);
```

### Wildcard Handling Strategy
```java
if (c == '.') {
    // Must try ALL children - any could lead to valid match
    for (TrieNode child : node.children.values()) {
        if (searchHelper(word, child, index + 1))
            return true;  // Short-circuit on first match
    }
    return false;  // No children led to valid match
}
```

### Base Case Design
```java
// Check if we've consumed entire word AND current node is word end
if (index == word.length())
    return node.endOfWord;
```

**Time Complexity:** O(m) addWord, O(n × 26^m) search worst case  
**Space Complexity:** O(ALPHABET_SIZE × N × M)