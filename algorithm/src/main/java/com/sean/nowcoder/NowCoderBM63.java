package com.sean.nowcoder;

public class NowCoderBM63 {

    public static int jumpFloor(int target) {
        if (target <= 1) {
            return 1;
        }
        int res = 0;
        int a = 1;
        int b = 1;
        for (int i = 2; i <= target; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

}
