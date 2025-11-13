package com.sean.leetcode.LeetCode3228;

/**
 * @Author xionghaiyang
 * @Date 2025-11-13 12:20
 * @Description https://leetcode.cn/problems/maximum-number-of-operations-to-move-ones-to-the-end
 * 3228. 将 1 移动到末尾的最大操作次数
 * 给你一个 二进制字符串 s。
 * 你可以对这个字符串执行 任意次 下述操作：
 * 选择字符串中的任一下标 i（ i + 1 < s.length ），该下标满足 s[i] == '1' 且 s[i + 1] == '0'。
 * 将字符 s[i] 向 右移 直到它到达字符串的末端或另一个 '1'。
 * 例如，对于 s = "010010"，如果我们选择 i = 1，结果字符串将会是 s = "000110"。
 * 返回你能执行的 最大 操作次数。
 * 1 <= s.length <= 10^5
 * s[i] 为 '0' 或 '1'。
 */
public class Solution {

    public int maxOperations(String s) {
        char[] str = s.toCharArray();
        int res = 0, cnt1 = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            if (str[i] == '1') {
                cnt1++;
            } else if (i > 0 && str[i - 1] == '1') {
                res += cnt1;
            }
        }
        return res;
    }

}
