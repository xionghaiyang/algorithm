package com.sean.leetcode.LeetCode850;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-16 19:14
 * @Description: https://leetcode.cn/problems/rectangle-area-ii/
 * 850. 矩形面积 II
 * 我们给出了一个（轴对齐的）二维矩形列表 rectangles 。
 * 对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）是矩形 i 左下角的坐标， (xi1, yi1) 是该矩形 左下角 的坐标， (xi2, yi2) 是该矩形 右上角 的坐标。
 * 计算平面中所有 rectangles 所覆盖的 总面积 。任何被两个或多个矩形覆盖的区域应只计算 一次 。
 * 返回 总面积 。因为答案可能太大，返回 109 + 7 的 模 。
 */
public class Solution {

    public int rectangleArea(int[][] rectangles) {
        int mod = 1000000007;
        int m = rectangles.length;
        Set<Integer> set = new HashSet<Integer>();
        for (int[] rectangle : rectangles) {
            // 下边界
            set.add(rectangle[1]);
            // 上边界
            set.add(rectangle[3]);
        }
        List<Integer> setList = new ArrayList<Integer>(set);
        Collections.sort(setList);
        int n = setList.size();
        int[] seg = new int[n - 1];
        List<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < m; ++i) {
            // 左边界
            list.add(new int[]{rectangles[i][0], i, 1});
            // 右边界
            list.add(new int[]{rectangles[i][2], i, -1});
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[2] - o2[2];
                }
            }
        });
        long res = 0;
        for (int i = 0; i < list.size(); ++i) {
            int j = i;
            while (j + 1 < list.size() && list.get(i)[0] == list.get(j + 1)[0]) {
                ++j;
            }
            if (j + 1 == list.size()) {
                break;
            }
            // 一次性地处理掉一批横坐标相同的左右边界
            for (int k = i; k <= j; ++k) {
                int[] arr = list.get(k);
                int idx = arr[1], diff = arr[2];
                int left = rectangles[idx][1], right = rectangles[idx][3];
                for (int x = 0; x < n - 1; ++x) {
                    if (left <= setList.get(x) && setList.get(x + 1) <= right) {
                        seg[x] += diff;
                    }
                }
            }
            int cover = 0;
            for (int k = 0; k < n - 1; ++k) {
                if (seg[k] > 0) {
                    cover += (setList.get(k + 1) - setList.get(k));
                }
            }
            res += (long) cover * (list.get(j + 1)[0] - list.get(j)[0]);
            i = j;
        }
        return (int) (res % mod);
    }

}
