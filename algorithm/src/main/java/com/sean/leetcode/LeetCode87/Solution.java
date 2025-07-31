package com.sean.leetcode.LeetCode87;

/**
 * @Author xionghaiyang
 * @Date 2025-07-31 17:57
 * @Description https://leetcode.cn/problems/scramble-string
 * 87. 扰乱字符串
 * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 在一个随机下标处将字符串分割成两个非空的子字符串。
 * 即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。
 * 即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 * 如果是，返回 true ；否则，返回 false 。
 * s1.length == s2.length
 * 1 <= s1.length <= 30
 * s1 和 s2 由小写英文字母组成
 */
public class Solution {

    private String s1;
    private String s2;
    private int[][][] dp;

    public boolean isScramble(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        int n = s1.length();
        dp = new int[n][n][n + 1];
        return process(0, 0, n);
    }

    //s1从i开始，s2从j开始，子串长度为length
    private boolean process(int i, int j, int length) {
        if (dp[i][j][length] != 0) {
            return dp[i][j][length] == 1;
        }
        //判断子串是否相等
        if (s1.substring(i, i + length).equals(s2.substring(j, j + length))) {
            dp[i][j][length] = 1;
            return true;
        }
        //判断是否存在字符c在两个子串中出现的次数不同
        if (!check(i, j, length)) {
            dp[i][j][length] = -1;
            return false;
        }
        //长度为1，一定满足
        if (length == 1) {
            dp[i][j][length] = 1;
            return true;
        }
        for (int k = 1; k < length; k++) {
            //不交换
            if (process(i, j, k) && process(i + k, j + k, length - k)) {
                dp[i][j][length] = 1;
                return true;
            }
            //交换
            if (process(i, j + length - k, k) && process(i + k, j, length - k)) {
                dp[i][j][length] = 1;
                return true;
            }
        }
        dp[i][j][length] = -1;
        return false;
    }

    private boolean check(int i, int j, int length) {
        int[] cnt = new int[26];
        for (int k = i; k < i + length; k++) {
            cnt[s1.charAt(k) - 'a']++;
        }
        for (int k = j; k < j + length; k++) {
            cnt[s2.charAt(k) - 'a']--;
        }
        for (int x : cnt) {
            if (x != 0) {
                return false;
            }
        }
        return true;
    }

}
