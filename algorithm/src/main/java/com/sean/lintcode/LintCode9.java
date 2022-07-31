package com.sean.lintcode;

import java.util.ArrayList;
import java.util.List;

public class LintCode9 {

    public static List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                ans.add("fizz");
            } else if (i % 3 != 0 && i % 5 == 0) {
                ans.add("buzz");
            } else if (i % 3 == 0 && i % 5 == 0) {
                ans.add("fizz buzz");
            } else {
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }

}
