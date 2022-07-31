package com.sean.lintcode;

public class LintCode2175 {

    public static int[] rounding(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= 0) {
                continue;
            }
            if (array[i] % 10 < 5) {
                array[i] = (array[i] / 10) * 10;
            } else {
                array[i] = (array[i] / 10 + 1) * 10;
            }
        }
        return array;
    }

}
