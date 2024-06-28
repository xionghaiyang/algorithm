package com.sean.leetcode.LeetCode347;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-02 17:40
 * @Description: https://leetcode.cn/problems/top-k-frequent-elements/description/
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。
 * 你可以按 任意顺序 返回答案。
 */
public class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.poll();
                pq.add(key);
            }
        }
        int[] res = new int[pq.size()];
        int index = 0;
        while (!pq.isEmpty()) {
            res[index++] = pq.poll();
        }
        return res;
    }

}
