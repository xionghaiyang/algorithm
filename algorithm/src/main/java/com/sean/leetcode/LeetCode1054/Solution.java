package com.sean.leetcode.LeetCode1054;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-15 08:48
 * @Description: https://leetcode.cn/problems/distant-barcodes/
 * 1054. 距离相等的条形码
 * 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
 * 请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。
 * 你可以返回任何满足该要求的答案，此题保证存在答案。
 */
public class Solution {

    public int[] rearrangeBarcodes1(int[] barcodes) {
        int n = barcodes.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(barcodes[i], map.getOrDefault(barcodes[i], 0) + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            heap.offer(new int[]{entry.getValue(), entry.getKey()});
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int[] p1 = heap.poll();
            int cx = p1[0];
            int x = p1[1];
            if (i == 0 || res[i - 1] != x) {
                res[i] = x;
                if (cx > 1) {
                    heap.offer(new int[]{cx - 1, x});
                }
            } else {
                int[] p2 = heap.poll();
                int cy = p2[0];
                int y = p2[1];
                res[i] = y;
                if (cy > 1) {
                    heap.offer(new int[]{cy - 1, y});
                }
                heap.offer(new int[]{cx, x});
            }
        }
        return res;
    }

    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        if (n < 2) {
            return barcodes;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(barcodes[i], map.getOrDefault(barcodes[i], 0) + 1);
        }
        int evenIndex = 0;
        int oddIndex = 1;
        int half = n / 2;
        int[] res = new int[n];
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            int x = entry.getKey();
            int cx = entry.getValue();
            while (cx > 0 && cx <= half && oddIndex < n) {
                res[oddIndex] = x;
                cx--;
                oddIndex += 2;
            }
            while (cx > 0) {
                res[evenIndex] = x;
                cx--;
                evenIndex += 2;
            }
        }
        return res;
    }

}
