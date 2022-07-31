package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode386 {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            ans.add(number);
            if (number * 10 <= n) {
                number *= 10;
            } else {
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                number++;
            }
        }
        return ans;
    }

}
