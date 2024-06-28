package com.sean.leetcode.LeetCode287;

/**
 * @Author xionghaiyang
 * @Date 2022-07-31 11:33
 * @Description https://leetcode.cn/problems/find-the-duplicate-number/
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 */
public class Solution {

    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int left = 1, right = n - 1, res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
                res = mid;
            }
        }
        return res;
    }

    public int findDuplicate1(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

}
