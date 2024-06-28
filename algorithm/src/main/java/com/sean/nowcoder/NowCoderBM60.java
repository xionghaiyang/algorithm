package com.sean.nowcoder;

import java.util.ArrayList;

public class NowCoderBM60 {

    public static void process(int left, int right, String temp, ArrayList<String> res, int n) {
        if (left == n && right == n) {
            res.add(temp);
            return;
        }
        if (left < n) {
            process(left + 1, right, temp + "(", res, n);
        }
        if (right < n && left > right) {
            process(left, right + 1, temp + ")", res, n);
        }
    }

    public static ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        process(0, 0, "", res, n);
        return res;
    }

}
