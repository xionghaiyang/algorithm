package com.sean.leetcode.LeetCode1954;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-24 19:26
 * @Description: https://leetcode.cn/problems/minimum-garden-perimeter-to-collect-enough-apples/
 * 1954. 收集足够苹果的最小花园周长
 * 给你一个用无限二维网格表示的花园，每一个 整数坐标处都有一棵苹果树。
 * 整数坐标 (i, j) 处的苹果树有 |i| + |j| 个苹果。
 * 你将会买下正中心坐标是 (0, 0) 的一块 正方形土地 ，且每条边都与两条坐标轴之一平行。
 * 给你一个整数 neededApples ，请你返回土地的 最小周长 ，使得 至少 有 neededApples 个苹果在土地 里面或者边缘上。
 * |x| 的值定义为：
 * 如果 x >= 0 ，那么值为 x
 * 如果 x < 0 ，那么值为 -x
 */
public class Solution {

    public long minimumPerimeter(long neededApples) {
        long left = 1, right = 100000, res = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (2 * mid * (mid + 1) * (mid * 2 + 1) >= neededApples) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res * 8;
    }

}
