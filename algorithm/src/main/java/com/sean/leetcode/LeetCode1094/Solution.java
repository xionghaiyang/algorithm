package com.sean.leetcode.LeetCode1094;

/**
 * @Author: xionghaiyang
 * @Date: 2023-12-02 20:33
 * @Description: https://leetcode.cn/problems/car-pooling
 * 1094. 拼车
 * 车上最初有 capacity 个空座位。
 * 车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 * 给定整数 capacity 和一个数组 trips ,
 * trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，
 * 接他们和放他们的位置分别是 fromi 和 toi 。
 * 这些位置是从汽车的初始位置向东的公里数。
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi <= 100
 * 0 <= fromi < toi <= 1000
 * 1 <= capacity <= 10^5
 */
public class Solution {

    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001];
        for (int[] trip : trips) {
            int num = trip[0], from = trip[1], to = trip[2];
            diff[from] += num;
            diff[to] -= num;
        }
        int cnt = 0;
        for (int v : diff) {
            cnt += v;
            if (cnt > capacity) {
                return false;
            }
        }
        return true;
    }

}
