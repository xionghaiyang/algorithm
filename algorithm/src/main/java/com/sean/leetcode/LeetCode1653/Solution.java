package com.sean.leetcode.LeetCode1653;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-06 08:15
 * @Description: https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced
 * 1653. 使字符串平衡的最少删除次数
 * 给你一个字符串 s ，它仅包含字符 'a' 和 'b'​​​​ 。
 * 你可以删除 s 中任意数目的字符，使得 s 平衡 。
 * 当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。
 * 请你返回使 s 平衡 的 最少 删除次数。
 * 1 <= s.length <= 10^5
 * s[i] 要么是 'a' 要么是 'b'​ 。​
 */
public class Solution {

    public int minimumDeletions(String s) {
        int n = s.length();
        int leftB = 0, rightA = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                rightA++;
            }
        }
        int res = rightA;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                rightA--;
            } else {
                leftB++;
            }
            res = Math.min(res, leftB + rightA);
        }
        return res;
    }

}
