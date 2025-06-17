package com.sean.leetcode.LeetCode347;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-02 17:40
 * @Description: https://leetcode.cn/problems/top-k-frequent-elements
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。
 * 你可以按 任意顺序 返回答案。
 * 1 <= nums.length <= 10^5
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 */
public class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (int key : map.keySet()) {
            if (heap.size() < k) {
                heap.offer(key);
            } else if (map.get(key) > map.get(heap.peek())) {
                heap.poll();
                heap.offer(key);
            }
        }
        int[] res = new int[heap.size()];
        for (int index = 0; !heap.isEmpty(); index++) {
            res[index] = heap.poll();
        }
        return res;
    }

    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int max = Collections.max(map.values());
        List<Integer>[] buckets = new ArrayList[max + 1];
        Arrays.setAll(buckets, i -> new ArrayList<>());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }
        int[] res = new int[k];
        for (int i = max, j = 0; i >= 0 && j < k; i--) {
            for (int x : buckets[i]) {
                res[j++] = x;
            }
        }
        return res;
    }

}
