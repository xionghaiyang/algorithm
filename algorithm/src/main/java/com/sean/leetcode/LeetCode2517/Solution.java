package com.sean.leetcode.LeetCode2517;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-01 08:08
 * @Description: https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/
 * 2517. 礼盒的最大甜蜜度
 * 给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。
 * 商店组合 k 类 不同 糖果打包成礼盒出售。
 * 礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。
 * 返回礼盒的 最大 甜蜜度。
 */
public class Solution {

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int left = 0, right = price[price.length - 1] - price[0];
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (check(price, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[] price, int k, int sweetness) {
        int prev = Integer.MIN_VALUE / 2;
        int cnt = 0;
        for (int p : price) {
            if (p - prev >= sweetness) {
                cnt++;
                prev = p;
            }
        }
        return cnt >= k;
    }

}
