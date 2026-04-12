package com.sean.leetcode.LeetCode1320;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-04-12 06:47
 * @Description https://leetcode.cn/problems/minimum-distance-to-type-a-word-using-two-fingers
 * 1320. 二指输入的的最小距离
 * 二指输入法定制键盘在 X-Y 平面上的布局如上图所示，其中每个大写英文字母都位于某个坐标处。
 * 例如字母 A 位于坐标 (0,0)，字母 B 位于坐标 (0,1)，字母 P 位于坐标 (2,3) 且字母 Z 位于坐标 (4,1)。
 * 给你一个待输入字符串 word，请你计算并返回在仅使用两根手指的情况下，键入该字符串需要的最小移动总距离。
 * 坐标 (x1,y1) 和 (x2,y2) 之间的 距离 是 |x1 - x2| + |y1 - y2|。
 * 注意，两根手指的起始位置是零代价的，不计入移动总距离。
 * 你的两根手指的起始位置也不必从首字母或者前两个字母开始。
 * 2 <= word.length <= 300
 * 每个 word[i] 都是一个大写英文字母。
 */
public class Solution {

    private static final int[][] dis = new int[26][26];
    private boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        final int col = 6;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                dis[i][j] = Math.abs(i / col - j / col) + Math.abs(i % col - j % col);
            }
        }
    }

    public int minimumDistance(String word) {
        init();
        char[] str = word.toCharArray();
        int n = word.length();
        int[][][] memo = new int[n][26][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int finger2 = 0; finger2 < 26; finger2++) {
            res = Math.min(res, process(str, memo, n - 2, str[n - 1] - 'A', finger2));
        }
        return res;
    }

    private int process(char[] str, int[][][] memo, int i, int finger1, int finger2) {
        if (i < 0) {
            return 0;
        }
        if (memo[i][finger1][finger2] != -1) {
            return memo[i][finger1][finger2];
        }
        int word = str[i] - 'A';
        int res = Math.min(process(str, memo, i - 1, word, finger2) + dis[finger1][word], process(str, memo, i - 1, finger1, word) + dis[finger2][word]);
        return memo[i][finger1][finger2] = res;
    }

}
