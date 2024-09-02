package com.sean.leetcode.LeetCode2024;

/**
 * @Author xionghaiyang
 * @Date 2024-09-02 07:51
 * @Description https://leetcode.cn/problems/maximize-the-confusion-of-an-exam/
 * 2024. 考试的最大困扰度
 * 一位老师正在出一场由 n 道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F' 表示）。
 * 老师想增加学生对自己做出答案的不确定性，方法是 最大化 有 连续相同 结果的题数。
 * （也就是连续出现 true 或者连续出现 false）。
 * 给你一个字符串 answerKey ，其中 answerKey[i] 是第 i 个问题的正确结果。
 * 除此以外，还给你一个整数 k ，表示你能进行以下操作的最多次数：
 * 每次操作中，将问题的正确答案改为 'T' 或者 'F' （也就是将 answerKey[i] 改为 'T' 或者 'F' ）。
 * 请你返回在不超过 k 次操作的情况下，最大 连续 'T' 或者 'F' 的数目。
 * n == answerKey.length
 * 1 <= n <= 5 * 10^4
 * answerKey[i] 要么是 'T' ，要么是 'F'
 * 1 <= k <= n
 */
public class Solution {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(process(answerKey.toCharArray(), k, 'T'), process(answerKey.toCharArray(), k, 'F'));
    }

    private int process(char[] str, int k, char ch) {
        int n = str.length;
        int res = 0;
        for (int left = 0, right = 0, sum = 0; right < n; right++) {
            sum += str[right] != ch ? 1 : 0;
            while (sum > k) {
                sum -= str[left++] != ch ? 1 : 0;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

}
