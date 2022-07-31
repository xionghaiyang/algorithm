package com.sean.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode436 {

    public static int[] findRightInterval1(int[][] intervals) {
        int n = intervals.length;
        int[][] startIntervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            startIntervals[i][0] = intervals[i][0];
            startIntervals[i][1] = i;
        }
        Arrays.sort(startIntervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            int target = -1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (startIntervals[mid][0] >= intervals[i][1]) {
                    target = startIntervals[mid][1];
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            res[i] = target;
        }
        return res;
    }

    public static int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] startIntervals = new int[n][2];
        int[][] endIntervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            startIntervals[i][0] = intervals[i][0];
            startIntervals[i][1] = i;
            endIntervals[i][0] = intervals[i][1];
            endIntervals[i][1] = i;
        }
        Arrays.sort(startIntervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Arrays.sort(endIntervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] res = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            while (j < n && endIntervals[i][0] > startIntervals[j][0]) {
                j++;
            }
            if (j < n) {
                res[endIntervals[i][1]] = startIntervals[j][1];
            } else {
                res[endIntervals[i][1]] = -1;
            }
        }
        return res;
    }

}
