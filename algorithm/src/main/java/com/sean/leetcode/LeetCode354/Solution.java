package com.sean.leetcode.LeetCode354;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-29 19:30
 * @Description: https://leetcode.cn/problems/russian-doll-envelopes
 * 354. 俄罗斯套娃信封问题
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 * 1 <= envelopes.length <= 10^5
 * envelopes[i].length == 2
 * 1 <= wi, hi <= 10^5
 */
public class Solution {

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int[] f = new int[n + 1];
        int len = 1;
        f[len] = envelopes[0][1];
        for (int i = 1; i < n; i++) {
            int x = envelopes[i][1];
            if (x > f[len]) {
                f[++len] = x;
            } else {
                int left = 1, right = len, j = 0;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (f[mid] < x) {
                        j = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                f[j + 1] = x;
            }
        }
        return len;
    }

}
