package com.sean.leetcode.LeetCode3403;

/**
 * @Author xionghaiyang
 * @Date 2025-06-04 08:02
 * @Description https://leetcode.cn/problems/find-the-lexicographically-largest-string-from-the-box-i
 * 3403. 从盒子中找出字典序最大的字符串 I
 * 给你一个字符串 word 和一个整数 numFriends。
 * Alice 正在为她的 numFriends 位朋友组织一个游戏。
 * 游戏分为多个回合，在每一回合中：
 * word 被分割成 numFriends 个 非空 字符串，且该分割方式与之前的任意回合所采用的都 不完全相同 。
 * 所有分割出的字符串都会被放入一个盒子中。
 * 在所有回合结束后，找出盒子中 字典序最大的 字符串。
 * 1 <= word.length <= 5 * 10^3
 * word 仅由小写英文字母组成。
 * 1 <= numFriends <= word.length
 */
public class Solution {

    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        int n = word.length();
        String res = "";
        for (int i = 0; i < n; i++) {
            String sub = word.substring(i, Math.min(i + n - numFriends + 1, n));
            if (sub.compareTo(res) > 0) {
                res = sub;
            }
        }
        return res;
    }

    public String answerString1(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        int n = word.length();
        int i = 0, j = 1;
        while (j < n) {
            int k = 0;
            while (j + k < n && word.charAt(i + k) == word.charAt(j + k)) {
                k++;
            }
            if (j + k < n && word.charAt(i + k) < word.charAt(j + k)) {
                int t = i;
                i = j;
                j = Math.max(j + 1, t + k + 1);
            } else {
                j += k + 1;
            }
        }
        return word.substring(i, Math.min(i + n - numFriends + 1, n));
    }

}
