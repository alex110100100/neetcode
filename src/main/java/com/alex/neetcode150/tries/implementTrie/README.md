# 208 - Implement Trie (Prefix Tree)

[Problem Link](https://leetcode.com/problems/implement-trie-prefix-tree/)

## Problem Statement

A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and search strings in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:
- `Trie()` Initializes the trie object.
- `void insert(String word)` Inserts the string word into the trie.
- `boolean search(String word)` Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
- `boolean startsWith(String prefix)` Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

## Examples

### Example 1:
```
Input: ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
       [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output: [null, null, true, false, true, null, true]

Explanation:
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
```

## Solution Approach

### Key Insight
- **Tree structure**: Each node represents a character position, edges represent characters
- **Path = word**: Root to any node represents a prefix, path to `isEndOfWord=true` represents complete word
- **Shared prefixes**: Common prefixes share the same path from root

### Trie Structure Design
```java
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
}

class PrefixTree {
    TrieNode root;
    
    public PrefixTree() {
        root = new TrieNode();
    }
}
```

**Why this structure:**
- **HashMap for children**: Flexible for any character, O(1) lookup
- **isEndOfWord flag**: Distinguishes complete words from just prefixes
- **Root node**: Empty, represents starting point for all words

### Insert Operation
```java
public void insert(String word) {
    TrieNode curr = root;
    
    for (Character c : word.toCharArray()) {
        curr.children.putIfAbsent(c, new TrieNode());
        curr = curr.children.get(c);
    }
    
    curr.isEndOfWord = true;
}
```

**Algorithm:**
1. Start at root
2. For each character, create child node if doesn't exist
3. Move to child node
4. Mark final node as end of word

### Search Operation
```java
public boolean search(String word) {
    TrieNode curr = root;
    
    for (Character c : word.toCharArray()) {
        curr = curr.children.get(c);
        
        if (curr == null)
            return false;
    }
    
    return curr.isEndOfWord;
}
```

**Algorithm:**
1. Traverse path character by character
2. If any character path doesn't exist, word not found
3. If reach end, check if it's marked as complete word

### StartsWith Operation
```java
public boolean startsWith(String prefix) {
    TrieNode curr = root;
    
    for (Character c : prefix.toCharArray()) {
        curr = curr.children.get(c);
        
        if (curr == null)
            return false;
    }
    
    return true;  // Don't need to check isEndOfWord
}
```

**Key difference from search:** Don't check `isEndOfWord` - any valid path means prefix exists

## Trie Visualization

### After inserting ["app", "apple", "application"]:
```
        root
         |
         a
         |
         p
         |
         p (isEndOfWord=true) ← "app" ends here
         |
         l
         |
         e (isEndOfWord=true) ← "apple" ends here
         |
         [remaining letters for "application"]
```

### Walkthrough: insert("apple"), search("app"), startsWith("app")

**Insert "apple":**
```
curr = root
c='a': putIfAbsent('a', new TrieNode()), curr = children['a']
c='p': putIfAbsent('p', new TrieNode()), curr = children['p']  
c='p': putIfAbsent('p', new TrieNode()), curr = children['p']
c='l': putIfAbsent('l', new TrieNode()), curr = children['l']
c='e': putIfAbsent('e', new TrieNode()), curr = children['e']
curr.isEndOfWord = true
```

**Search "app":**
```
curr = root
c='a': curr = children['a'] (exists)
c='p': curr = children['p'] (exists)
c='p': curr = children['p'] (exists)
return curr.isEndOfWord (false, since "app" wasn't inserted)
```

**StartsWith "app":**
```
curr = root
c='a': curr = children['a'] (exists)
c='p': curr = children['p'] (exists)  
c='p': curr = children['p'] (exists)
return true (path exists, regardless of isEndOfWord)
```

## Complexity Analysis

**Time Complexity:**
- **Insert**: O(m) where m = length of word
- **Search**: O(m) where m = length of word
- **StartsWith**: O(m) where m = length of prefix

**Space Complexity:** O(ALPHABET_SIZE × N × M)
- N = number of words
- M = average length of words
- ALPHABET_SIZE = 26 for lowercase letters
- In worst case (no shared prefixes), each character needs separate node

## Key Concepts Learned

### Trie vs Other Data Structures
```java
// HashSet: O(m) search, but no prefix functionality
Set<String> words = new HashSet<>();

// Trie: O(m) search AND O(m) prefix search
// Plus: autocomplete, spell check, word games
```

### HashMap vs Array for Children
```java
// Your approach: HashMap (flexible, any characters)
Map<Character, TrieNode> children = new HashMap<>();

// Alternative: Array (faster, only lowercase a-z)
TrieNode[] children = new TrieNode[26];
// children[c - 'a'] for character c
```

### putIfAbsent() Pattern
```java
// Elegant one-liner
curr.children.putIfAbsent(c, new TrieNode());

// Alternative verbose approach
if (!curr.children.containsKey(c)) {
    curr.children.put(c, new TrieNode());
}
```

### isEndOfWord Importance
- **Without flag**: Can't distinguish "app" vs "apple" if both inserted
- **With flag**: Can tell if a path represents complete word vs just prefix
- **Search vs StartsWith**: Search checks flag, StartsWith ignores it

## Implementation Notes

### Common Applications
- **Autocomplete**: Find all words with given prefix
- **Spell checker**: Suggest corrections for misspelled words
- **Word games**: Boggle, Scrabble word validation
- **IP routing**: Longest prefix matching in routers

### Extension Ideas
```java
// Count words with prefix
public int countWordsWithPrefix(String prefix) {
    // Find prefix node, then DFS count isEndOfWord nodes
}

// Delete word
public void delete(String word) {
    // More complex - need to handle cleanup of unused nodes
}

// Get all words with prefix
public List<String> getWordsWithPrefix(String prefix) {
    // Find prefix node, then DFS collect all words
}
```

### Alternative TrieNode Designs
```java
// Approach 1: Your approach (HashMap)
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
}

// Approach 2: Array for lowercase only
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord;
}

// Approach 3: Store character in node
class TrieNode {
    char ch;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
}
```

**Time Complexity:** O(m) for all operations  
**Space Complexity:** O(ALPHABET_SIZE × N × M)