/**
 * 1031. Maximum Sum of Two Non-Overlapping Subarrays
 * Medium
 *
 * Given an integer array nums and two integers firstLen and secondLen, return the maximum sum of elements in two non-overlapping subarrays with lengths firstLen and secondLen.
 *
 * The array with length firstLen could occur before or after the array with length secondLen, but they have to be non-overlapping.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
 * Output: 20
 * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
 *
 * Example 2:
 *
 * Input: nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
 * Output: 29
 * Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
 *
 * Example 3:
 *
 * Input: nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
 * Output: 31
 * Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
 *
 *
 *
 * Constraints:
 *
 *     1 <= firstLen, secondLen <= 1000
 *     2 <= firstLen + secondLen <= 1000
 *     firstLen + secondLen <= nums.length <= 1000
 *     0 <= nums[i] <= 1000
 */
class Solution {
  // T: O(n), S:O(n)
  public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
    int[] preSum = new int[nums.length];
    preSum[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      preSum[i] = nums[i] + preSum[i - 1];
    }
    int lmax = preSum[firstLen - 1], rmax = preSum[secondLen - 1];
    int res = preSum[firstLen + secondLen - 1];
    for (int i = firstLen + secondLen; i < preSum.length; i++) {
      // note that on i'th index, both two subarrray exist, so the left side subarray's limit end is
      // preSum[i - second] when firstLen is on the left
      // sliding the window length firstLen / secondLen to update the max subarray on the left side
      // and because of this, the subarray on the right which has to end before i , now has to end at i
      // to ensure non-overlapping

      // firsLen is on the left
      lmax = Math.max(lmax, preSum[i - secondLen] - preSum[i - secondLen - firstLen]);
      // secondLen is on the left
      rmax = Math.max(rmax, preSum[i - firstLen] - preSum[i - firstLen - secondLen]);

      // to ensure non overlapping, the subarray on the right side must end at idx i,
      // cause the lmax or rmax could come from just the right most it could reach, leaving the two subarray adjacent to each other

      // per say, at a given array, the right most R elements are given for the right subarray, find the Lmax for the remaining left elements
      res = Math.max(res, Math.max(lmax + preSum[i] - preSum[i - secondLen], rmax + preSum[i] - preSum[i - firstLen]));
    }
    return res;
  }
}
