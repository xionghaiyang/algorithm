package com.sean.course03.lesson07;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-04-11 13:05
 * @Description 想象一下标准的俄罗斯方块游戏，X轴是积木最终下落到底的轴线
 * 下面是这个游戏的简化版：
 * 1）只会下落正方形积木
 * 2）[a,b] -> 代表一个边长为b的正方形积木，积木左边缘沿着X = a这条线从上方掉落
 * 3）认为整个X轴都可能接住积木，也就是说简化版游戏是没有整体的左右边界的
 * 4）没有整体的左右边界，所以简化版游戏不会消除积木，因为不会有哪一层被填满。
 * 给定一个N*2的二维数组matrix，可以代表N个积木依次掉落，
 * 返回每一次掉落之后的最大高度
 */
public class Code03_FallingSquares {

    public static class SegmentTree {
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
