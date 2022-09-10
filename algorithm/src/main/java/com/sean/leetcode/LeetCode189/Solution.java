package com.sean.leetcode.LeetCode189;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-09 19:58
 * @Description: https://leetcode.cn/problems/rotate-array/?plan=algorithms&plan_progress=zq05vcm
 * 189. 轮转数组
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 */
public class Solution {

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        k = k % n;
        int gcd = gcd(k, n);
        for (int start = 0; start < gcd; start++) {
            int cur = start;
            int pre = nums[start];
            do {
                int next = (cur + k) % n;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                cur = next;
            } while (start != cur);
        }
    }

    private int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

}
