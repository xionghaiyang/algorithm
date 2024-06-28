package com.sean.leetcode.LeetCode397;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-23 18:27
 * @Description: https://leetcode.cn/problems/integer-replacement/description/
 * 397. 整数替换
 * 给定一个正整数 n ，你可以做如下操作：
 * 如果 n 是偶数，则用 n / 2替换 n 。
 * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
 * 返回 n 变为 1 所需的 最小替换次数 。
 */
public class Solution {

    Map<Integer, Integer> map = new HashMap<>();

    public int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if (!map.containsKey(n)) {
            if (n % 2 == 0) {
                map.put(n, 1 + integerReplacement(n / 2));
            } else {
                map.put(n, 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1)));
            }
        }
        return map.get(n);
    }

    public int integerReplacement1(int n) {
        int res = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                res++;
                n /= 2;
            } else if (n % 4 == 1) {
                res += 2;
                n /= 2;
            } else {
                if (n == 3) {
                    res += 2;
                    n = 1;
                } else {
                    res += 2;
                    n = n / 2 + 1;
                }
            }
        }
        return res;
    }

}
