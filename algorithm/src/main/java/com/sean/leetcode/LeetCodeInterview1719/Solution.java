package com.sean.leetcode.LeetCodeInterview1719;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-26 08:20
 * @Description: https://leetcode.cn/problems/missing-two-lcci/
 * 面试题 17.19. 消失的两个数字
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 * 以任意顺序返回这两个数字均可。
 */
public class Solution {

    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        xor = xor & (-xor);
        int res1 = 0, res2 = 0;
        for (int num : nums) {
            if ((num & xor) != 0) {
                res1 ^= num;
            } else {
                res2 ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & xor) != 0) {
                res1 ^= i;
            } else {
                res2 ^= i;
            }
        }
        return new int[]{res1, res2};
    }

}
