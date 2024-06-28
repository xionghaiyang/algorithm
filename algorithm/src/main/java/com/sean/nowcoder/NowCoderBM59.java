package com.sean.nowcoder;

import java.util.Arrays;

public class NowCoderBM59 {

    private static int res;

    public static boolean isValid(int[] pos, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (col == pos[i] || Math.abs(row - i) == Math.abs(col - pos[i])) {
                return false;
            }
        }
        return true;
    }

    public static void process(int n, int row, int[] pos) {
        if (row == n) {
            res++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(pos, row, i)) {
                pos[row] = i;
                process(n, row + 1, pos);
            }
        }
    }

    public static int Nqueen(int n) {
        res = 0;
        int[] pos = new int[n];
        Arrays.fill(pos, 0);
        process(n, 0, pos);
        return res;
    }

}
