package com.sean.nowcoder;

public class NowCoderBM18 {

    public static boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        int n = array.length;
        if (array[0].length == 0) {
            return false;
        }
        int m = array[0].length;
        for (int i = n - 1, j = 0; i >= 0 && j < m; ) {
            if (array[i][j] > target) {
                i--;
            } else if (array[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

}
