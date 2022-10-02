/*
Lets assume this is our array: [4,5,6,7,0,1,2];

First point : We need to find the pivot.
    *** In this array we have 2 ascending numbers sub array (for discussion I think it as sub array).
    *** In between this two sub array our pivot is lies in the last index of the first sub array.
    How can we find this pivot? Let's talk about that,
    Some possible cases for binary search: (because array is sorted)
        1 if the previous element is greater than the mid element then return mid - 1.
        2 if mid element is greater than the next element then reutrn mid.
        3 if mid element is less than start element then it implies that pivot can be found 
          in the left side of the array. So end will become mid - 1.
        4 if mid element is greater than start element then it implies that pivot can be 
          found in the right side of the array. So start will become mid + 1.
          
        * if any of the return statement didn't hit inside the while loop then return -1 outside the 
          while loop.
        * while loop condition: (in edge cases)
            lets say it is a sorted array: [1, 2, 3, 4, 5, 6]
            Here mid element is 3. So it actually hit the 4th cases and then start element become 4.
            Again mid element is 5. So again it hit the 4th cases. Now start element become 6 and end
            element is also 6. So that's the condition we are going to apply for while loop. 
            
Second point: We need a binary search method to search the target if it is exist.

Third point : In the search method.
        * If findPivot method returns -1 then we will apply binary search from 0th index till end.
        
        * If findPivot method returns index of pivot then there will be some possible cases:
            * if element at pivot is equal to target then return the element.
            * if our target is less than first element of the nums array then we will apply binary
              search from pivot index + 1 till nus.length.
            * if our target is greater than first element of the nums array then we will apply binary
              search from 0th index till pivot index - 1.
*/

class Solution {
    public int search(int[] nums, int target) {
        // Store the index of the pivot
        int pindex = findPivot(nums);
            
        if (pindex == -1){
            return binarySearch(nums, target, 0, nums.length - 1);
        }
        
        
        if (nums[pindex] == target) return pindex;
        
        if (target < nums[0])
            return binarySearch(nums, target, pindex + 1, nums.length - 1);
        else
            return binarySearch(nums, target, 0, pindex - 1);

    }
    
    public int binarySearch(int[] nums, int target, int start, int end){
        while (start <= end){
            int mid = start + (end - start) / 2;
            
            if (nums[mid] < target)
                start = mid + 1;
            else if (nums[mid] > target)
                end = mid - 1;
            else
                return mid;
        }
        
        // If the target is not found.
        return -1;
    }
    
    
    public int findPivot(int[] nums){
        int start = 0;
        int end = nums.length - 1;
        
        while (start < end){
            int mid = start + (end - start) / 2;
            
            if (mid > start && nums[mid] < nums[mid - 1]) return mid - 1;
            
            if (mid < end && nums[mid] > nums[mid + 1]) return  mid;
            
            if (nums[mid] < nums[start])
                end = mid - 1;
            else
                start = mid + 1;
        }
        // If the array is not sorted
        return -1;
    }
}