package com.sean.leetcode.LeetCode1652;

/**
 * @Author xionghaiyang
 * @Date 2024-05-05 11:17
 * @Description https://leetcode.cn/problems/defuse-the-bomb/
 * 1652. 拆炸弹
 * 你有一个炸弹需要拆除，时间紧迫！
 * 你的情报员会给你一个长度为 n 的 循环 数组 code 以及一个密钥 k 。
 * 为了获得正确的密码，你需要替换掉每一个数字。
 * 所有数字会 同时 被替换。
 * 如果 k > 0 ，将第 i 个数字用 接下来 k 个数字之和替换。
 * 如果 k < 0 ，将第 i 个数字用 之前 k 个数字之和替换。
 * 如果 k == 0 ，将第 i 个数字用 0 替换。
 * 由于 code 是循环的， code[n-1] 下一个元素是 code[0] ，且 code[0] 前一个元素是 code[n-1] 。
 * 给你 循环 数组 code 和整数密钥 k ，请你返回解密后的结果来拆除炸弹！
 */
public class Solution {

    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        if (n == 0) {
            return new int[n];
        }
        int[] res = new int[n];
        int[] arr = new int[n * 2];
        System.arraycopy(code, 0, arr, 0, n);
        System.arraycopy(code, 0, arr, n, n);
        int left = k > 0 ? 1 : n + k;
        int right = k > 0 ? k : n - 1;
        int w = 0;
        for (int i = left; i <= right; i++) {
            w += arr[i];
        }
        for (int i = 0; i < n; i++) {
            res[i] = w;
            w -= arr[left++];
            w += arr[++right];
        }
        return res;
    }

}
