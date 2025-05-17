package com.sean.leetcode.LeetCode75;

/**
 * @Author xionghaiyang
 * @Date 2025-05-17 08:52
 * @Description https://leetcode.cn/problems/sort-colors
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 */
public class Solution {

    public void sortColors(int[] nums) {
        int n = nums.length;
        int[] cnt = new int[3];
        for (int num : nums) {
            cnt[num]++;
        }
        int index = 0;
        for (int i = 0; i < 3; i++) {
            while (cnt[i] > 0) {
                nums[index++] = i;
                cnt[i]--;
            }
        }
    }

    public void sortColors1(int[] nums) {
        int n = nums.length;
        int index0 = 0, index2 = n - 1;
        for (int i = 0; i <= index2; i++) {
            while (i <= index2 && nums[i] == 2) {
                swap(nums, i, index2--);
            }
            if (nums[i] == 0) {
                swap(nums, i, index0++);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
