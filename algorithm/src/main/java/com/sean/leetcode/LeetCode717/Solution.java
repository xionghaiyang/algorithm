package com.sean.leetcode.LeetCode717;

/**
 * @Author xionghaiyang
 * @Date 2025-11-18 12:34
 * @Description https://leetcode.cn/problems/1-bit-and-2-bit-characters
 * 717. 1 比特与 2 比特字符
 * 有两种特殊字符：
 * 第一种字符可以用一比特 0 表示
 * 第二种字符可以用两比特（10 或 11）表示
 * 给你一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一个一比特字符，则返回 true 。
 * 1 <= bits.length <= 1000
 * bits[i] 为 0 或 1
 */
public class Solution {

    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length, i = 0;
        while (i < n - 1) {
            i += bits[i] + 1;
        }
        return i == n - 1;
    }

}
