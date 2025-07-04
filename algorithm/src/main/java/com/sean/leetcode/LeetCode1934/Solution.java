package com.sean.leetcode.LeetCode1934;

/**
 * @Author xionghaiyang
 * @Date 2025-07-05 06:33
 * @Description https://leetcode.cn/problems/find-lucky-integer-in-an-array
 * 1394. 找出数组中的幸运数
 * 在整数数组中，如果一个整数的出现频次和它的数值大小相等，我们就称这个整数为「幸运数」。
 * 给你一个整数数组 arr，请你从中找出并返回一个幸运数。
 * 如果数组中存在多个幸运数，只需返回 最大 的那个。
 * 如果数组中不含幸运数，则返回 -1 。
 * 1 <= arr.length <= 500
 * 1 <= arr[i] <= 500
 */
public class Solution {

    public int findLucky(int[] arr) {
        int n = arr.length;
        int[] cnt = new int[n + 1];
        int max = 0;
        for (int num : arr) {
            if (num > n) {
                continue;
            }
            if (num > max) {
                max = num;
            }
            cnt[num]++;
        }
        for (int i = max; i > 0; i--) {
            if (cnt[i] == i) {
                return i;
            }
        }
        return -1;
    }

}
