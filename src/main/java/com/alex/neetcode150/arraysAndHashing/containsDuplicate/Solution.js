const containsDuplicate = (nums) => {
    const set = new Set();

    for (const num of nums) {
        if (set.has(num)) return true;
        set.add(num);
    }

    return false;
  };


const testContainsDuplicate = () => {
    const nums1 = [1, 2, 3, 4, 5];      // No duplicates
    const nums2 = [1, 2, 3, 1];         // Contains duplicate (1)
    const nums3 = [5, 5, 6, 7, 8];      // Contains duplicate (5)
    const nums4 = [1, 1, 1, 1, 1];      // Contains duplicate (1)
  
    console.log(containsDuplicate(nums1));   // Expected output: false
    console.log(containsDuplicate(nums2));   // Expected output: true
    console.log(containsDuplicate(nums3));   // Expected output: true
    console.log(containsDuplicate(nums4));   // Expected output: true
  };
  
  testContainsDuplicate();