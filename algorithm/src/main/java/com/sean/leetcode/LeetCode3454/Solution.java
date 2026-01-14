package com.sean.leetcode.LeetCode3454;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @Author xionghaiyang
 * @Date 2026-01-14 09:39
 * @Description https://leetcode.cn/problems/separate-squares-ii
 * 3454. 分割正方形 II
 * 给你一个二维整数数组 squares ，其中 squares[i] = [xi, yi, li] 表示一个与 x 轴平行的正方形的左下角坐标和正方形的边长。
 * 找到一个最小的 y 坐标，它对应一条水平线，该线需要满足它以上正方形的总面积 等于 该线以下正方形的总面积。
 * 答案如果与实际答案的误差在 10^-5 以内，将视为正确答案。
 * 注意：正方形 可能会 重叠。
 * 重叠区域只 统计一次 。
 * 1 <= squares.length <= 5 * 10^4
 * squares[i] = [xi, yi, li]
 * squares[i].length == 3
 * 0 <= xi, yi <= 10^9
 * 1 <= li <= 10^9
 * 所有正方形的总面积不超过 10^15。
 */
public class Solution {

    public class SegmentTree {
        private int[] count;
        private int[] covered;
        private int[] xs;
        private int n;

        public SegmentTree(int[] xs) {
            this.xs = xs;
            n = xs.length - 1;
            count = new int[4 * n];
            covered = new int[4 * n];
        }

        private void modify(int qLeft, int qRight, int qVal, int left, int right, int pos) {
            if (xs[right + 1] <= qLeft || xs[left] >= qRight) {
                return;
            }
            if (qLeft <= xs[left] && xs[right + 1] <= qRight) {
                count[pos] += qVal;
            } else {
                int mid = left + ((right - left) >> 1);
                modify(qLeft, qRight, qVal, left, mid, pos * 2 + 1);
                modify(qLeft, qRight, qVal, mid + 1, right, pos * 2 + 2);
            }
            if (count[pos] > 0) {
                covered[pos] = xs[right + 1] - xs[left];
            } else {
                if (left == right) {
                    covered[pos] = 0;
                } else {
                    covered[pos] = covered[pos * 2 + 1] + covered[pos * 2 + 2];
                }
            }
        }

        public void update(int qLeft, int qRight, int qVal) {
            modify(qLeft, qRight, qVal, 0, n - 1, 0);
        }

        public int query() {
            return covered[0];
        }
    }

    public double separateSquares(int[][] squares) {
        //存储事件:(y坐标，类型，左边界，右边界)
        List<int[]> events = new ArrayList<>();
        TreeSet<Integer> xsSet = new TreeSet<>();
        for (int[] square : squares) {
            int x = square[0], y = square[1], l = square[2], xr = x + l, yr = y + l;
            events.add(new int[]{y, 1, x, xr});
            events.add(new int[]{yr, -1, x, xr});
            xsSet.add(x);
            xsSet.add(xr);
        }
        //按照y坐标排序事件
        events.sort((a, b) -> Integer.compare(a[0], b[0]));
        //离散化坐标
        int[] xs = xsSet.stream().mapToInt(i -> i).toArray();
        //初始化线段树
        SegmentTree segmentTree = new SegmentTree(xs);
        List<Long> pSum = new ArrayList<>();
        List<Integer> widths = new ArrayList<>();
        Long totalArea = 0L;
        int prev = events.get(0)[0];
        for (int[] event : events) {
            int y = event[0], delta = event[1], xl = event[2], xr = event[3];
            int len = segmentTree.query();
            totalArea += (long) len * (y - prev);
            segmentTree.update(xl, xr, delta);
            //记录前缀和和宽度
            pSum.add(totalArea);
            widths.add(segmentTree.query());
            prev = y;
        }
        //计算目标面积（向上取整的一半）
        long target = (long) (totalArea + 1) / 2;
        int i = binarySearch(pSum, target);
        double area = pSum.get(i);
        int width = widths.get(i), height = events.get(i)[0];
        return height + (totalArea - area * 2) / (width * 2.0);
    }

    private int binarySearch(List<Long> list, long target) {
        int left = 0, right = list.size() - 1, res = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (list.get(mid) < target) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

}
