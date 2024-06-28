package com.sean.leetcode.LeetCode1553;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-12 12:37
 * @Description https://leetcode.cn/problems/minimum-number-of-days-to-eat-n-oranges/
 * 1553. 吃掉 N 个橘子的最少天数
 * 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
 * 吃掉一个橘子。
 * 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
 * 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
 * 每天你只能从以上 3 种方案中选择一种方案。
 * 请你返回吃掉所有 n 个橘子的最少天数。
 */
public class Solution {

    Map<Integer, Integer> map = new HashMap<>();

    public int minDays(int n) {
        if (n <= 1) {
            return n;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        map.put(n, Math.min(n % 2 + 1 + minDays(n / 2), n % 3 + 1 + minDays(n / 3)));
        return map.get(n);
    }

}
