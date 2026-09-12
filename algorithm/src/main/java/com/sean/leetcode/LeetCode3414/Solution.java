package com.sean.leetcode.LeetCode3414;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-12 16:05
 * @Description: https://leetcode.cn/problems/maximum-score-of-non-overlapping-intervals
 * 3414. 不重叠区间的最大得分
 * 给你一个二维整数数组 intervals，其中 intervals[i] = [li, ri, weighti]。
 * 区间 i 的起点为 li，终点为 ri，权重为 weighti。
 * 你最多可以选择 4 个互不重叠 的区间。
 * 所选择区间的 得分 定义为这些区间权重的总和。
 * 返回一个至多包含 4 个下标且 字典序最小 的数组，表示从 intervals 中选中的互不重叠且得分最大的区间。
 * 如果两个区间没有任何重叠点，则称二者 互不重叠 。
 * 特别地，如果两个区间共享左边界或右边界，也认为二者重叠。
 * 1 <= intervals.length <= 5 * 10^4
 * intervals[i].length == 3
 * intervals[i] = [li, ri, weighti]
 * 1 <= li <= ri <= 10^9
 * 1 <= weighti <= 10^9
 */
public class Solution {

    public class Info {
        private int l;
        private int r;
        private int weight;
        private int i;

        public Info(int l, int r, int weight, int i) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.i = i;
        }
    }

    public class Pair {
        private long sum;
        private List<Integer> id;

        public Pair(long sum, List<Integer> id) {
            this.sum = sum;
            this.id = id;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Info[] infos = new Info[n];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            infos[i] = new Info(interval.get(0), interval.get(1), interval.get(2), i);
        }
        Arrays.sort(infos, (p, q) -> p.r - q.r);
        Pair[][] f = new Pair[n + 1][5];
        Arrays.setAll(f[0], i -> new Pair(0, new ArrayList<>()));
        for (int i = 0; i < n; i++) {
            Info info = infos[i];
            int k = binarySearch(infos, i, info.l);
            f[i + 1][0] = new Pair(0, new ArrayList<>());
            for (int j = 1; j < 5; j++) {
                long s1 = f[i][j].sum;
                long s2 = f[k + 1][j - 1].sum + info.weight;
                if (s1 > s2) {
                    f[i + 1][j] = f[i][j];
                    continue;
                }
                List<Integer> newId = new ArrayList<>(f[k + 1][j - 1].id);
                newId.add(info.i);
                Collections.sort(newId);
                if (s1 == s2 && compare(f[i][j].id, newId) < 0) {
                    newId = f[i][j].id;
                }
                f[i + 1][j] = new Pair(s2, newId);
            }
        }
        return f[n][4].id.stream().mapToInt(v -> v).toArray();
    }

    //返回r < upper的最大下标
    private int binarySearch(Info[] infos, int right, int upper) {
        int left = -1;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (infos[mid].r < upper) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int compare(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) - b.get(i);
            }
        }
        return a.size() - b.size();
    }

}
