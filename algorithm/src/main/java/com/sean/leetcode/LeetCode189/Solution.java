package com.sean.leetcode.LeetCode189;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-09 19:58
 * @Description: https://leetcode.cn/problems/rotate-array/?plan=algorithms&plan_progress=zq05vcm
 * 189. 轮转数组
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 */
public class Solution {

    public void rotate(int[] nums, int k) {
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

    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
