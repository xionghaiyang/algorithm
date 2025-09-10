package com.sean.leetcode.LeetCode947;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-09-10 10:12
 * @Description https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column
 * 947. 移除最多的同行或同列石头
 * n 块石头放置在二维平面中的一些整数坐标点上。
 * 每个坐标点上最多只能有一块石头。
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 10^4
 * 不会有两块石头放在同一个坐标点上
 */
public class Solution {

    public class UnionFind {
        private Map<Integer, Integer> parent;
        private Map<Integer, Integer> size;
        private int set;

        public UnionFind() {
            parent = new HashMap<>();
            size = new HashMap<>();
            set = 0;
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {
                return -1;
            }
            List<Integer> list = new ArrayList<>();
            while (x != parent.get(x)) {
                list.add(x);
                x = parent.get(x);
            }
            for (int y : list) {
                parent.put(y, x);
            }
            return x;
        }

        public int add(int x) {
            parent.put(x, x);
            size.put(x, 1);
            set++;
            return x;
        }

        public void union(int x, int y) {
            int fx = find(x);
            if (fx == -1) {
                fx = add(x);
            }
            int fy = find(y);
            if (fy == -1) {
                fy = add(y);
            }
            if (fx == fy) {
                return;
            }
            if (size.get(fx) >= size.get(fy)) {
                parent.put(fy, fx);
                size.put(fx, size.get(fx) + size.get(fy));
            } else {
                parent.put(fx, fy);
                size.put(fy, size.get(fx) + size.get(fy));
            }
            set--;
        }

        public int getSet() {
            return set;
        }
    }

    public int removeStones(int[][] stones) {
        final int DIFF = 10_001;
        UnionFind unionFind = new UnionFind();
        for (int[] stone : stones) {
            unionFind.union(stone[0], stone[1] + DIFF);
        }
        return stones.length - unionFind.getSet();
    }

}
