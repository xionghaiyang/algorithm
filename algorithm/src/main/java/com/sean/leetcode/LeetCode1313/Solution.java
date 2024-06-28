package com.sean.leetcode.LeetCode1313;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-09 13:32
 * @Description: https://leetcode.cn/problems/decompress-run-length-encoded-list/
 * 1313. 解压缩编码列表
 * 给你一个以行程长度编码压缩的整数列表 nums 。
 * 考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ），
 * 每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
 * 请你返回解压后的列表。
 */
public class Solution {

    public int[] decompressRLElist(int[] nums) {
        if (nums.length <= 1) {
            return new int[]{nums[0]};
        }
        int len = 0;
        for (int i = 0; i < nums.length; i += 2) {
            len += nums[i];
        }
        int[] res = new int[len];
        for (int i = 0, j = 0; i < nums.length; i += 2) {
            for (int k = 0; k < nums[i]; k++) {
                res[j++] = nums[i + 1];
            }
        }
        return res;
    }

}
