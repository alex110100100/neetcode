const isAnagram = (s, t) => {
    if (s.length !== t.length) return false;
    return reorder(s) === reorder(t); /* Time O(N * logN) | Space O(N) */
};

const reorder = (str) => str
    .split('')                         /* Time O(N)          | Space O(N) */
    .sort((a, b) => a.localeCompare(b))/* Time O(N * log(N)) | Space O(1 || log(N)) */
    .join('');                         /* Time O(N)          | Space O(N) */



const isAnagram2 = (s, t) => {
  if (s.length !== t.length) return false;

  const counts = new Array(26).fill(0);

  for (let i = 0; i < s.length; i++) {
    const c1 = s.charCodeAt(i);
    const c2 = t.charCodeAt(i);

    counts[c1 - 97]++;
    counts[c2 - 97]--;
  }

  for (const count of counts) {
    if (count !== 0) return false;
  }

  return true;
}



// Testing
console.log(isAnagram("anagram", "nagaram")); // Output: true
console.log(isAnagram("rat", "car")); // Output: false

console.log(isAnagram2("anagram", "nagaram")); // Output: true
console.log(isAnagram2("rat", "car")); // Output: false

