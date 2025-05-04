package com.sean.leetcode.LeetCode1128;

/**
 * @Author xionghaiyang
 * @Date 2025-05-04 07:50
 * @Description https://leetcode.cn/problems/number-of-equivalent-domino-pairs
 * 1128. 等价多米诺骨牌对的数量
 * 给你一组多米诺骨牌 dominoes 。
 * 形式上，dominoes[i] = [a, b] 与 dominoes[j] = [c, d] 等价 当且仅当 (a == c 且 b == d) 或者 (a == d 且 b == c) 。
 * 即一张骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌。
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 * 1 <= dominoes.length <= 4 * 10^4
 * dominoes[i].length == 2
 * 1 <= dominoes[i][j] <= 9
 */
public class Solution {

    public int numEquivDominoPairs(int[][] dominoes) {
        int[] cnt = new int[100];
        int res = 0;
        for (int[] domino : dominoes) {
            int key = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            res += cnt[key];
            cnt[key]++;
        }
        return res;
    }

}
