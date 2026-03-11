package com.sean.leetcode.LeetCodeInterview0503;

/**
 * @Author xionghaiyang
 * @Date 2026-03-11 12:46
 * @Description https://leetcode.cn/problems/reverse-bits-lcci
 * 面试题 05.03. 翻转数位
 * 给定一个32位整数 num，你可以将一个数位从0变为1。
 * 请编写一个程序，找出你能够获得的最长的一串1的长度。
 */
public class Solution {

    public int reverseBits(int num) {
        int cur = 0, insert = 0, res = 1;
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                cur++;
                insert++;
            } else {
                insert = cur + 1;
                cur = 0;
            }
            res = Math.max(res, insert);
        }
        return res;
    }

}
