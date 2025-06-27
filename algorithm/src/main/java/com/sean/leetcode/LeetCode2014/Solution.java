package com.sean.leetcode.LeetCode2014;

/**
 * @Author xionghaiyang
 * @Date 2025-06-27 05:50
 * @Description https://leetcode.cn/problems/longest-subsequence-repeated-k-times
 * 2014. 重复 K 次的最长子序列
 * 给你一个长度为 n 的字符串 s ，和一个整数 k 。
 * 请你找出字符串 s 中 重复 k 次的 最长子序列 。
 * 子序列 是由其他字符串删除某些（或不删除）字符派生而来的一个字符串。
 * 如果 seq * k 是 s 的一个子序列，其中 seq * k 表示一个由 seq 串联 k 次构造的字符串，那么就称 seq 是字符串 s 中一个 重复 k 次 的子序列。
 * 举个例子，"bba" 是字符串 "bababcba" 中的一个重复 2 次的子序列，因为字符串 "bbabba" 是由 "bba" 串联 2 次构造的，而 "bbabba" 是字符串 "bababcba" 的一个子序列。
 * 返回字符串 s 中 重复 k 次的最长子序列  。
 * 如果存在多个满足的子序列，则返回 字典序最大 的那个。
 * 如果不存在这样的子序列，返回一个 空 字符串。
 * n == s.length
 * 2 <= k <= 2000
 * 2 <= n < k * 8
 * s 由小写英文字母组成
 */
public class Solution {

    private char[] res;
    private int resLen = 0;

    public String longestSubsequenceRepeatedK(String s, int k) {
        char[] str = s.toCharArray();
        int[] cnt = new int[26];
        for (char c : str) {
            cnt[c - 'a']++;
        }
        StringBuilder tmp = new StringBuilder();
        for (int i = 25; i >= 0; i--) {
            String c = String.valueOf((char) ('a' + i));
            for (int j = 0; j < cnt[i] / k; j++) {
                tmp.append(c);
            }
        }
        char[] arr = tmp.toString().toCharArray();
        res = new char[arr.length];
        permute(arr, k, str);
        return new String(res, 0, resLen);
    }

    private void permute(char[] nums, int k, char[] s) {
        int n = nums.length;
        char[] path = new char[n];
        //onPath[j]表示nums[j]是否已经填入排列
        boolean[] onPath = new boolean[n];
        dfs(0, nums, path, onPath, k, s);
    }

    private void dfs(int i, char[] nums, char[] path, boolean[] onPath, int k, char[] s) {
        process(path, i, k, s);
        if (i == nums.length) {
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            //如果nums[j]已填入排列,continue
            //如果nums[j]和前一个数nums[j-1]相等，且nums[j-1]没填入排列，continue
            if (onPath[j] || (j > 0 && nums[j] == nums[j - 1] && !onPath[j - 1])) {
                continue;
            }
            //填入排列
            path[i] = nums[j];
            //nums[j]已填入排列（注意标记的是下标，不是值）
            onPath[j] = true;
            //填排列的下一个数
            dfs(i + 1, nums, path, onPath, k, s);
            //恢复现场
            onPath[j] = false;
            //注意path无需恢复现场，直接覆盖path[i]就行
        }
    }

    private void process(char[] seq, int seqLen, int k, char[] s) {
        if (seqLen > resLen || (seqLen == resLen && compare(seq, res, resLen) > 0)) {
            if (isSubsequence(seq, seqLen, k, s)) {
                System.arraycopy(seq, 0, res, 0, seqLen);
                resLen = seqLen;
            }
        }
    }

    //比较a和b的字典序大小
    private int compare(char[] a, char[] b, int n) {
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return a[i] - b[i];
            }
        }
        return 0;
    }

    //返回seq*k是否为s的子序列
    private boolean isSubsequence(char[] seq, int n, int k, char[] s) {
        int i = 0;
        for (char c : s) {
            if (seq[i % n] == c) {
                i++;
                if (i == n * k) {
                    return true;
                }
            }
        }
        return false;
    }

}
