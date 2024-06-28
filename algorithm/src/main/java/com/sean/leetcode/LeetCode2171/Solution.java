package com.sean.leetcode.LeetCode2171;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-18 13:04
 * @Description: https://leetcode.cn/problems/removing-minimum-number-of-magic-beans/
 * 2171. 拿出最少数目的魔法豆
 * 给定一个 正整数 数组 beans ，其中每个整数表示一个袋子里装的魔法豆的数目。
 * 请你从每个袋子中 拿出 一些豆子（也可以 不拿出），使得剩下的 非空 袋子中（即 至少还有一颗 魔法豆的袋子）魔法豆的数目 相等。
 * 一旦把魔法豆从袋子中取出，你不能再将它放到任何袋子中。
 * 请返回你需要拿出魔法豆的 最少数目。
 */
public class Solution {

    public long minimumRemoval(int[] beans) {
        int n = beans.length;
        Arrays.sort(beans);
        long total = 0;
        for (int i = 0; i < n; i++) {
            total += beans[i];
        }
        long res = total;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, total - (long) beans[i] * (n - i));
        }
        return res;
    }

}
