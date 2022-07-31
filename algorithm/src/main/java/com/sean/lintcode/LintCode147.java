package com.sean.lintcode;

import java.util.ArrayList;
import java.util.List;

public class LintCode147 {

    public List<Integer> getNarcissisticNumbers(int n) {
        List<Integer> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        int start;
        if (n == 1) {
            start = 0;
        } else {
            start = (int) Math.pow(10, n - 1);
        }
        int end = (int) Math.pow(10, n) - 1;
        for (int i = start; i <= end; i++) {
            if (i == pow(i, n)) {
                res.add(i);
            }
        }
        return res;
    }

    private int pow(int num, int n) {
        int res = 0;
        while (num != 0) {
            res += Math.pow(num % 10, n);
            num /= 10;
        }
        return res;
    }

}
