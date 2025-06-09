package com.sean.leetcode.LeetCode3579;

/**
 * @Author xionghaiyang
 * @Date 2025-06-09 23:05
 * @Description https://leetcode.cn/problems/minimum-steps-to-convert-string-with-operations/
 * 3579. 字符串转换需要的最小操作数
 * 给你两个长度相等的字符串 word1 和 word2。
 * 你的任务是将 word1 转换成 word2。
 * 为此，可以将 word1 分割成一个或多个连续子字符串。
 * 对于每个子字符串 substr，可以执行以下操作：
 * 替换：将 substr 中任意一个索引处的字符替换为另一个小写字母。
 * 交换：交换 substr 中任意两个字符的位置。
 * 反转子串：将 substr 进行反转。
 * 每种操作计为 一次 ，并且每个子串中的每个字符在每种操作中最多只能使用一次（即任何字符的下标不能参与超过一次替换、交换或反转操作）。
 * 返回将 word1 转换为 word2 所需的 最小操作数 。
 * 子串 是字符串中任意一个连续且非空的字符序列。
 * 1 <= word1.length == word2.length <= 100
 * word1 和 word2 仅由小写英文字母组成。
 */
public class Solution {

    public int minOperations(String word1, String word2) {
        int n = word1.length();
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        //dp[i+1] 表示前缀[0...i]需要的最少操作次数
        int[] dp = new int[n + 1];
        //空串无需操作
        //dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int res = Integer.MAX_VALUE;
            int[][] cnt = new int[26][26];
            int op = 0;
            for (int j = i; j >= 0; j--) {
                //不反转
                int x = str1[j] - 'a';
                int y = str2[j] - 'a';
                if (x != y) {
                    if (cnt[y][x] > 0) {
                        cnt[y][x]--;
                    } else {
                        cnt[x][y]++;
                        op++;
                    }
                }
                //反转
                int[][] revCnt = new int[26][26];
                int revOp = 1;
                for (int p = j; p <= i; p++) {
                    x = str1[p] - 'a';
                    y = str2[i + j - p] - 'a';
                    if (x == y) {
                        continue;
                    }
                    if (revCnt[y][x] > 0) {
                        revCnt[y][x]--;
                    } else {
                        revCnt[x][y]++;
                        revOp++;
                    }
                }
                res = Math.min(res, dp[j] + Math.min(op, revOp));
            }
            dp[i + 1] = res;
        }
        return dp[n];
    }

    private int op;
    private int[][] cnt;

    public int minOperations1(String word1, String word2) {
        int n = word1.length();
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        int[][] revOp = new int[n][n];
        //中心扩展
        //i为偶数表示奇长度子串，i为奇数表示偶长度子串
        for (int i = 0; i < 2 * n - 1; i++) {
            cnt = new int[26][26];
            op = 1;
            int left = i / 2;
            int right = (i + 1) / 2;
            while (left >= 0 && right < n) {
                update(str1[left], str2[right]);
                if (left != right) {
                    update(str1[right], str2[left]);
                }
                revOp[left][right] = op;
                left--;
                right++;
            }
        }
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int res = Integer.MAX_VALUE;
            cnt = new int[26][26];
            //不反转时的最小操作次数
            op = 0;
            for (int j = i; j >= 0; j--) {
                update(str1[j], str2[j]);
                res = Math.min(res, dp[j] + Math.min(op, revOp[j][i]));
            }
            dp[i + 1] = res;
        }
        return dp[n];
    }

    private void update(char x, char y) {
        if (x == y) {
            return;
        }
        x -= 'a';
        y -= 'a';
        if (cnt[y][x] > 0) {
            cnt[y][x]--;
        } else {
            cnt[x][y]++;
            op++;
        }
    }

}
