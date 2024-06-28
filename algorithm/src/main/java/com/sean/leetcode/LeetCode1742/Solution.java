package com.sean.leetcode.LeetCode1742;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-23 08:21
 * @Description: https://leetcode.cn/problems/maximum-number-of-balls-in-a-box/
 * 1742. 盒子中小球的最大数量
 * 你在一家生产小球的玩具厂工作，有 n 个小球，编号从 lowLimit 开始，到 highLimit 结束（包括 lowLimit 和 highLimit ，即 n == highLimit - lowLimit + 1）。
 * 另有无限数量的盒子，编号从 1 到 infinity 。
 * 你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。
 * 例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，而编号 10 的小球应当放入编号 1 + 0 = 1 的盒子。
 * 给你两个整数 lowLimit 和 highLimit ，返回放有最多小球的盒子中的小球数量。如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
 */
public class Solution {

    public int countBalls(int lowLimit, int highLimit) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = lowLimit; i <= highLimit; i++) {
            int box = 0, x = i;
            while (x != 0) {
                box += x % 10;
                x /= 10;
            }
            map.put(box, map.getOrDefault(box, 0) + 1);
            res = Math.max(res, map.get(box));
        }
        return res;
    }

}
