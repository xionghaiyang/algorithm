package com.sean.leetcode.LeetCode153;

/**
 * @Author xionghaiyang
 * @Date 2025-06-14 11:39
 * @Description https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array
 * 153. 寻找旋转排序数组中的最小值
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。
 * 请你找出并返回数组中的 最小元素 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */
public class Solution {

    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1, res = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] <= nums[n - 1]) {
                //mid在第二段（或者nums只有一段）
                //mid要么是最小值，要么在最小值右边
                res = mid;
                right = mid - 1;
            } else {
                //nums一定被分成左右两个递增段，第一段的所有元素均大于第二段的所有元素
                //mid在第一段，最小值在第二段
                left = mid + 1;
            }
        }
        return nums[res];
    }

}
