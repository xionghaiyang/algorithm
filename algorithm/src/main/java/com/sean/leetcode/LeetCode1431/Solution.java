package com.sean.leetcode.LeetCode1431;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-29 15:20
 * @Description: https://leetcode.cn/problems/kids-with-the-greatest-number-of-candies/?envType=study-plan-v2&envId=leetcode-75
 * 1431. 拥有最多糖果的孩子
 * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
 * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，
 * 此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。
 */
public class Solution {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        if (candies == null || candies.length == 0) {
            return new ArrayList<>();
        }
        int n = candies.length;
        int max = candies[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, candies[i]);
        }
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (candies[i] + extraCandies >= max) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return res;
    }

}
