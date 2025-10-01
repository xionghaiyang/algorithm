package com.sean.leetcode.LeetCode1518;

/**
 * @Author xionghaiyang
 * @Date 2025-10-01 09:18
 * @Description https://leetcode.cn/problems/water-bottles
 * 1518. 换水问题
 * 超市正在促销，你可以用 numExchange 个空水瓶从超市兑换一瓶水。
 * 最开始，你一共购入了 numBottles 瓶水。
 * 如果喝掉了水瓶中的水，那么水瓶就会变成空的。
 * 给你两个整数 numBottles 和 numExchange ，返回你 最多 可以喝到多少瓶水。
 * 1 <= numBottles <= 100
 * 2 <= numExchange <= 100
 */
public class Solution {

    public int numWaterBottles(int numBottles, int numExchange) {
        return numBottles + (numBottles - 1) / (numExchange - 1);
    }

}
