package com.sean.leetcode.LeetCode2213;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-13 05:56
 * @Description: https://leetcode.cn/problems/longest-substring-of-one-repeating-character
 * 2213. 由单个字符重复的最长子字符串
 * 给你一个下标从 0 开始的字符串 s 。
 * 另给你一个下标从 0 开始、长度为 k 的字符串 queryCharacters ，一个下标从 0 开始、长度也是 k 的整数 下标 数组 queryIndices ，这两个都用来描述 k 个查询。
 * 第 i 个查询会将 s 中位于下标 queryIndices[i] 的字符更新为 queryCharacters[i] 。
 * 返回一个长度为 k 的数组 lengths ，其中 lengths[i] 是在执行第 i 个查询 之后 s 中仅由 单个字符重复 组成的 最长子字符串 的 长度 。
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * k == queryCharacters.length == queryIndices.length
 * 1 <= k <= 10^5
 * queryCharacters 由小写英文字母组成
 * 0 <= queryIndices[i] < s.length
 */
public class Solution {

    private char[] s;
    private int[] pre;
    private int[] suf;
    private int[] max;

    private void build(int index, int left, int right) {
        if (left == right) {
            pre[index] = suf[index] = max[index] = 1;
            return;
        }
        int mid = left + ((right - left) >> 1);
        build(index << 1, left, mid);
        build(index << 1 | 1, mid + 1, right);
        maintain(index, left, right);
    }

    private void maintain(int index, int left, int right) {
        pre[index] = pre[index << 1];
        suf[index] = suf[index << 1 | 1];
        max[index] = Math.max(max[index << 1], max[index << 1 | 1]);
        int mid = left + ((right - left) >> 1);
        if (s[mid - 1] == s[mid]) {
            if (suf[index << 1] == mid - left + 1) {
                pre[index] += pre[index << 1 | 1];
            }
            if (pre[index << 1 | 1] == right - mid) {
                suf[index] += suf[index << 1];
            }
            max[index] = Math.max(max[index], suf[index << 1] + pre[index << 1 | 1]);
        }
    }

    private void update(int index, int left, int right, int i) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        if (i <= mid) {
            update(index << 1, left, mid, i);
        } else {
            update(index << 1 | 1, mid + 1, right, i);
        }
        maintain(index, left, right);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.s = s.toCharArray();
        int n = s.length(), m = queryCharacters.length();
        pre = new int[n << 2];
        suf = new int[n << 2];
        max = new int[n << 2];
        build(1, 1, n);
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            this.s[queryIndices[i]] = queryCharacters.charAt(i);
            update(1, 1, n, queryIndices[i] + 1);
            res[i] = max[1];
        }
        return res;
    }

}
