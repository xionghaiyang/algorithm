package com.sean.leetcode.LeetCode2126;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-29 15:40
 * @Description: https://leetcode.cn/problems/destroying-asteroids/description/
 * 2126. 摧毁小行星
 * 给你一个整数 mass ，它表示一颗行星的初始质量。
 * 再给你一个整数数组 asteroids ，其中 asteroids[i] 是第 i 颗小行星的质量。
 * 你可以按 任意顺序 重新安排小行星的顺序，然后让行星跟它们发生碰撞。
 * 如果行星碰撞时的质量 大于等于 小行星的质量，那么小行星被 摧毁 ，并且行星会 获得 这颗小行星的质量。否则，行星将被摧毁。
 * 如果所有小行星 都 能被摧毁，请返回 true ，否则返回 false 。
 */
public class Solution {

    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long mass1 = mass;
        for (int asteroid : asteroids) {
            if (mass1 < asteroid) {
                return false;
            }
            mass1 += asteroid;
        }
        return true;
    }

}
