package com.sean.leetcode.LeetCode1980;

/**
 * @Author xionghaiyang
 * @Date 2026-03-08 07:56
 * @Description https://leetcode.cn/problems/find-unique-binary-string
 * 1980. 找出不同的二进制字符串
 * 给你一个字符串数组 nums ，该数组由 n 个 互不相同 的二进制字符串组成，且每个字符串长度都是 n 。
 * 请你找出并返回一个长度为 n 且 没有出现 在 nums 中的二进制字符串。
 * 如果存在多种答案，只需返回 任意一个 即可。
 * n == nums.length
 * 1 <= n <= 16
 * nums[i].length == n
 * nums[i] 为 '0' 或 '1'
 * nums 中的所有字符串 互不相同
 */
public class Solution {

    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            res[i] = (char) (nums[i].charAt(i) ^ 1);
        }
        return String.valueOf(res);
    }

}
