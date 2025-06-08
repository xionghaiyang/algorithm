package com.sean.leetcode.LeetCode3572;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-06-08 07:50
 * @Description https://leetcode.cn/problems/maximize-ysum-by-picking-a-triplet-of-distinct-xvalues/
 * 3572. 选择不同 X 值三元组使 Y 值之和最大
 * 给你两个整数数组 x 和 y，长度均为 n。
 * 你必须选择三个 不同 的下标 i ，j 和 k，满足以下条件：
 * x[i] != x[j]
 * x[j] != x[k]
 * x[k] != x[i]
 * 你的目标是在满足这些条件下 最大化 y[i] + y[j] + y[k] 的值。
 * 返回通过选择这样一组三元组下标所能获得的 最大 可能和。
 * 如果不存在这样的三元组，返回 -1。
 * n == x.length == y.length
 * 3 <= n <= 10^5
 * 1 <= x[i], y[i] <= 10^6
 */
public class Solution {

    public int maxSumDistinctTriplet(int[] x, int[] y) {
        int n = x.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(x[i])) {
                map.put(x[i], y[i]);
            } else if (map.get(x[i]) < y[i]) {
                map.put(x[i], y[i]);
            }
        }
        if (map.size() < 3) {
            return -1;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> a - b);
        for (int value : map.values()) {
            heap.offer(value);
            if (heap.size() > 3) {
                heap.poll();
            }
        }
        return heap.poll() + heap.poll() + heap.poll();
    }

}
