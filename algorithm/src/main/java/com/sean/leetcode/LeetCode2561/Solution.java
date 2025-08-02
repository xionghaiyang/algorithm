package com.sean.leetcode.LeetCode2561;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-08-02 05:56
 * @Description https://leetcode.cn/problems/rearranging-fruits
 * 2561. 重排水果
 * 你有两个果篮，每个果篮中有 n 个水果。
 * 给你两个下标从 0 开始的整数数组 basket1 和 basket2 ，用以表示两个果篮中每个水果的交换成本。
 * 你想要让两个果篮相等。
 * 为此，可以根据需要多次执行下述操作：
 * 选中两个下标 i 和 j ，并交换 basket1 中的第 i 个水果和 basket2 中的第 j 个水果。
 * 交换的成本是 min(basket1i,basket2j) 。
 * 根据果篮中水果的成本进行排序，如果排序后结果完全相同，则认为两个果篮相等。
 * 返回使两个果篮相等的最小交换成本，如果无法使两个果篮相等，则返回 -1 。
 * basket1.length == bakste2.length
 * 1 <= basket1.length <= 10^5
 * 1 <= basket1i,basket2i <= 10^9
 */
public class Solution {

    public long minCost(int[] basket1, int[] basket2) {
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        int min = Integer.MAX_VALUE;
        for (int x : basket1) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
            min = Math.min(min, x);
        }
        for (int x : basket2) {
            freq.put(x, freq.getOrDefault(x, 0) - 1);
            min = Math.min(min, x);
        }
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int key = entry.getKey();
            int cnt = entry.getValue();
            if ((cnt & 1) != 0) {
                return -1;
            }
            for (int i = 0; i < Math.abs(cnt) / 2; i++) {
                list.add(key);
            }
        }
        Collections.sort(list);
        long res = 0;
        for (int i = 0; i < list.size() / 2; i++) {
            res += Math.min(2 * min, list.get(i));
        }
        return res;
    }

}
