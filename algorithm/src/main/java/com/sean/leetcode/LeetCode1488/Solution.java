package com.sean.leetcode.LeetCode1488;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-10-07 09:17
 * @Description https://leetcode.cn/problems/avoid-flood-in-the-city
 * 1488. 避免洪水泛滥
 * 你的国家有无数个湖泊，所有湖泊一开始都是空的。
 * 当第 n 个湖泊下雨前是空的，那么它就会装满水。
 * 如果第 n 个湖泊下雨前是 满的 ，这个湖泊会发生 洪水 。
 * 你的目标是避免任意一个湖泊发生洪水。
 * 给你一个整数数组 rains ，其中：
 * rains[i] > 0 表示第 i 天时，第 rains[i] 个湖泊会下雨。
 * rains[i] == 0 表示第 i 天没有湖泊会下雨，你可以选择 一个 湖泊并 抽干 这个湖泊的水。
 * 请返回一个数组 ans ，满足：
 * ans.length == rains.length
 * 如果 rains[i] > 0 ，那么ans[i] == -1 。
 * 如果 rains[i] == 0 ，ans[i] 是你第 i 天选择抽干的湖泊。
 * 如果有多种可行解，请返回它们中的 任意一个 。
 * 如果没办法阻止洪水，请返回一个 空的数组 。
 * 请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。
 * 但如果你选择抽干一个空的湖泊，那么将无事发生。
 * 1 <= rains.length <= 10^5
 * 0 <= rains[i] <= 10^9
 */
public class Solution {

    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        //lake -> 装满日
        Map<Integer, Integer> fullDay = new HashMap<>();
        //未被使用的抽水日
        TreeSet<Integer> dryDay = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int lake = rains[i];
            if (lake == 0) {
                res[i] = 1;
                dryDay.add(i);
                continue;
            }
            Integer j = fullDay.get(lake);
            if (j != null) {
                //必须在j之后，i之前把lake抽干
                //选一个最早的未被使用的抽水日，如果选晚的，可能会导致其他湖没有可用的抽水日
                Integer d = dryDay.higher(j);
                if (d == null) {
                    return new int[]{};
                }
                res[d] = lake;
                dryDay.remove(d);
            }
            res[i] = -1;
            fullDay.put(lake, i);
        }
        return res;
    }

}
