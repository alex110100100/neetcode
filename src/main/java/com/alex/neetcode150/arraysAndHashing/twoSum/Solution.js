const twoSum1 = (nums, target) => {
    for (let i = 0; i < nums.length - 1; i++) {
        for (let j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] === target) return [i, j];
        }
    }
    return null;
}




const twoSum2 = (nums, target) => {
    const valuesToIndex = new Map();

    for (let i = 0; i < nums.length; i++) {
        valuesToIndex.set(nums[i], i);
    }

    for (let i = 0; i < nums.length; i++) {
        let curr = nums[i];
        let difference = target - curr;
        
        if (valuesToIndex.has(difference)) {
            if (valuesToIndex.get(difference) != i) return [valuesToIndex.get(difference), i];
        }
    }
    return null;
}


const twoSum3 = (nums, target) => {
    const diffToIndex = new Map();

    for (let i = 0; i < nums.length; i++) {
        let curr = nums[i];

        if (diffToIndex.has(curr)) return [diffToIndex.get(curr), i];

        diffToIndex.set(target - nums[i], i);
    }
    return null;
}


console.log(twoSum3([3, 2, 4], 6));