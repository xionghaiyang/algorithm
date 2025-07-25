package com.sean.leetcode.LeetCode215;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-02 17:55
 * @Description: https://leetcode.cn/problems/kth-largest-element-in-an-array
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class Solution {

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSort(nums, 0, n - 1, n - k);
    }

    private int quickSort(int[] nums, int left, int right, int k) {
        swap(nums, left + (int) ((right - left + 1) * Math.random()), right);
        int[] equalArea = partition(nums, left, right);
        if (k < equalArea[0]) {
            return quickSort(nums, left, equalArea[0] - 1, k);
        } else if (k > equalArea[1]) {
            return quickSort(nums, equalArea[1] + 1, right, k);
        } else {
            return nums[k];
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int[] partition(int[] nums, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        int less = left - 1, more = right, index = left;
        while (index < more) {
            if (nums[index] == nums[right]) {
                index++;
            } else if (nums[index] < nums[right]) {
                swap(nums, index++, ++less);
            } else {
                swap(nums, index, --more);
            }
        }
        swap(nums, more, right);
        return new int[]{less + 1, more};
    }

}
