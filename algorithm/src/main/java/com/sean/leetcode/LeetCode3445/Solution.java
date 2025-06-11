package com.sean.leetcode.LeetCode3445;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-06-11 06:19
 * @Description https://leetcode.cn/problems/maximum-difference-between-even-and-odd-frequency-ii
 * 3445. 奇偶频次间的最大差值 II
 * 给你一个字符串 s 和一个整数 k 。
 * 请你找出 s 的子字符串 subs 中两个字符的出现频次之间的 最大 差值，freq[a] - freq[b] ，其中：
 * subs 的长度 至少 为 k 。
 * 字符 a 在 subs 中出现奇数次。
 * 字符 b 在 subs 中出现偶数次。
 * 返回 最大 差值。
 * 注意 ，subs 可以包含超过 2 个 互不相同 的字符。.
 * 子字符串 是字符串中的一个连续字符序列。
 * 3 <= s.length <= 3 * 10^4
 * s 仅由数字 '0' 到 '4' 组成。
 * 输入保证至少存在一个子字符串是由一个出现奇数次的字符和一个出现偶数次的字符组成。
 * 1 <= k <= s.length
 */
public class Solution {

    public int maxDifference(String s, int k) {
        int n = s.length();
        int res = -n;
        int[] curFreq = new int[5];
        int[] preFreq = new int[5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (x == y) {
                    continue;
                }
                Arrays.fill(curFreq, 0);
                Arrays.fill(preFreq, 0);
                int[][] minFreq = {{n, n}, {n, n}};
                for (int left = 0, right = 0; right < n; right++) {
                    curFreq[s.charAt(right) - '0']++;
                    while (right - left + 1 >= k && curFreq[x] > preFreq[x] && curFreq[y] > preFreq[y]) {
                        int p = preFreq[x] & 1;
                        int q = preFreq[y] & 1;
                        minFreq[p][q] = Math.min(minFreq[p][q], preFreq[x] - preFreq[y]);
                        preFreq[s.charAt(left) - '0']++;
                        left++;
                    }
                    res = Math.max(res, curFreq[x] - curFreq[y] - minFreq[curFreq[x] & 1 ^ 1][curFreq[y] & 1]);
                }
            }
        }
        return res;
    }

}
