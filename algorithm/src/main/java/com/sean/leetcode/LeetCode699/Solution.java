package com.sean.leetcode.LeetCode699;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-07-28 18:37
 * @Description https://leetcode.cn/problems/falling-squares/
 * 699. 掉落的方块
 * 在二维平面上的 x 轴上，放置着一些方块。
 * 给你一个二维整数数组 positions ，其中 positions[i] = [lefti, sideLengthi] 表示：第 i 个方块边长为 sideLengthi ，其左侧边与 x 轴上坐标点 lefti 对齐。
 * 每个方块都从一个比目前所有的落地方块更高的高度掉落而下。
 * 方块沿 y 轴负方向下落，直到着陆到 另一个正方形的顶边 或者是 x 轴上 。
 * 一个方块仅仅是擦过另一个方块的左侧边或右侧边不算着陆。
 * 一旦着陆，它就会固定在原地，无法移动。
 * 在每个方块掉落后，你必须记录目前所有已经落稳的 方块堆叠的最高高度 。
 * 返回一个整数数组 ans ，其中 ans[i] 表示在第 i 块方块掉落后堆叠的最高高度。
 */
public class Solution {

    public class SegmentTree {
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            int N = size + 1;
            max = new int[N << 2];
            change = new int[N << 2];
            update = new boolean[N << 2];
        }

        private void pushUp(int index) {
            max[index] = Math.max(max[index << 1], max[index << 1 | 1]);
        }

        //lSize表示左子树元素节点个数，rSize表示右子树节点个数
        private void pushDown(int index, int lSize, int rSize) {
            if (update[index]) {
                update[index << 1] = true;
                update[index << 1 | 1] = true;
                change[index << 1] = change[index];
                change[index << 1 | 1] = change[index];
                max[index << 1] = change[index];
                max[index << 1 | 1] = change[index];
                update[index] = false;
            }
        }

        public void update(int L, int R, int C, int l, int r, int index) {
            if (L <= l && r <= R) {
                update[index] = true;
                change[index] = C;
                max[index] = C;
                return;
            }
            int mid = l + ((r - l) >> 1);
            pushDown(index, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, index << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, index << 1 | 1);
            }
            pushUp(index);
        }

        public int query(int L, int R, int l, int r, int index) {
            if (L <= l && r <= R) {
                return max[index];
            }
            int mid = l + ((r - l) >> 1);
            pushDown(index, mid - l + 1, r - mid);
            int left = 0;
            int right = 0;
            if (L <= mid) {
                left = query(L, R, l, mid, index << 1);
            }
            if (R > mid) {
                right = query(L, R, mid + 1, r, index << 1 | 1);
            }
            return Math.max(left, right);
        }

    }

    private Map<Integer, Integer> index(int[][] positions) {
        TreeSet<Integer> pos = new TreeSet<>();
        for (int[] arr : positions) {
            pos.add(arr[0]);
            pos.add(arr[0] + arr[1] - 1);
        }
        Map<Integer, Integer> res = new HashMap<>();
        int count = 0;
        for (int index : pos) {
            res.put(index, ++count);
        }
        return res;
    }

    public List<Integer> fallingSquares(int[][] positions) {
        Map<Integer, Integer> map = index(positions);
        int N = map.size();
        SegmentTree segmentTree = new SegmentTree(N);
        int max = 0;
        List<Integer> res = new ArrayList<>();
        for (int[] arr : positions) {
            int L = map.get(arr[0]);
            int R = map.get(arr[0] + arr[1] - 1);
            int height = segmentTree.query(L, R, 1, N, 1) + arr[1];
            max = Math.max(max, height);
            res.add(max);
            segmentTree.update(L, R, height, 1, N, 1);
        }
        return res;
    }

}
