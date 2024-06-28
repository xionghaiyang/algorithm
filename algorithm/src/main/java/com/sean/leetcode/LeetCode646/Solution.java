package com.sean.leetcode.LeetCode646;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author xionghaiyang
 * @Date 2022-09-03 09:23
 * @Description https://leetcode.cn/problems/maximum-length-of-pair-chain/
 * 646. 最长数对链
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 */
public class Solution {

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int cur = Integer.MIN_VALUE, res = 0;
        for (int[] pair : pairs) {
            if (cur < pair[0]) {
                cur = pair[1];
                res++;
            }
        }
        return res;
    }

}
