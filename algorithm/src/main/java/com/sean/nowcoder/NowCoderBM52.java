package com.sean.nowcoder;

public class NowCoderBM52 {

    public static int[] FindNumsAppearOnce(int[] array) {
        int xor = 0;
        for (int num : array) {
            xor ^= num;
        }
        int rightOne = xor & (-xor);
        int x = 0;
        for (int num : array) {
            if ((num & rightOne) != 0) {
                x ^= num;
            }
        }
        int y = x ^ xor;
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }
        return new int[]{x, y};
    }

}
