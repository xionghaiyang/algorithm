package com.sean.leetcode.LeetCode823;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-08-29 07:27
 * @Description: https://leetcode.cn/problems/binary-trees-with-factors/
 * 823. 带因子的二叉树
 * 给出一个含有不重复整数元素的数组 arr ，每个整数 arr[i] 均大于 1。
 * 用这些整数来构建二叉树，每个整数可以使用任意次数。其中：每个非叶结点的值应等于它的两个子结点的值的乘积。
 * 满足条件的二叉树一共有多少个？答案可能很大，返回 对 109 + 7 取余 的结果。
 */
public class Solution {

    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        //dp[i]表示以arr[i]为根结点的带因子的二叉树数目
        long[] dp = new long[n];
        long res = 0, mod = 1000000007;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int left = 0, right = i - 1; left <= right; left++) {
                while (right >= left && (long) arr[left] * arr[right] > arr[i]) {
                    right--;
                }
                if (right >= left && (long) arr[left] * arr[right] == arr[i]) {
                    if (right != left) {
                        dp[i] = (dp[i] + dp[left] * dp[right] * 2) % mod;
                    } else {
                        dp[i] = (dp[i] + dp[left] * dp[right]) % mod;
                    }
                }
            }
            res = (res + dp[i]) % mod;
        }
        return (int) res;
    }

}
