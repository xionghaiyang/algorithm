package com.sean.leetcode.LeetCode2363;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-28 08:02
 * @Description: https://leetcode.cn/problems/merge-similar-items/
 * 2363. 合并相似的物品
 * 给你两个二维整数数组 items1 和 items2 ，表示两个物品集合。每个数组 items 有以下特质：
 * items[i] = [valuei, weighti] 其中 valuei 表示第 i 件物品的 价值 ，weighti 表示第 i 件物品的 重量 。
 * items 中每件物品的价值都是 唯一的 。
 * 请你返回一个二维数组 ret，其中 ret[i] = [valuei, weighti]， weighti 是所有价值为 valuei 物品的 重量之和 。
 * 注意：ret 应该按价值 升序 排序后返回。
 */
public class Solution {

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] item : items1) {
            map.put(item[0], map.getOrDefault(item[0], 0) + item[1]);
        }
        for (int[] item : items2) {
            map.put(item[0], map.getOrDefault(item[0], 0) + item[1]);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            List<Integer> pair = new ArrayList<>();
            pair.add(key);
            pair.add(value);
            res.add(pair);
        }
        Collections.sort(res, (a, b) -> a.get(0) - b.get(0));
        return res;
    }

}
