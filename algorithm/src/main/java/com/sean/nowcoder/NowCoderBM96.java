package com.sean.nowcoder;

import java.util.Arrays;

public class NowCoderBM96 {

    public static int minmumNumberOfHost(int n, int[][] startEnd) {
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = startEnd[i][0];
            end[i] = startEnd[i][1];
        }
        Arrays.sort(start, 0, start.length);
        Arrays.sort(end, 0, end.length);
        int res = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (start[i] >= end[j]) {
                j++;
            } else {
                res++;
            }
        }
        return res;
    }

}
