package com.sean.nowcoder;

import java.util.ArrayList;

public class NowCoderBM74 {

    private static String nums = "";

    public static void dfs(String s, ArrayList<String> res, int step, int index) {
        String cur = "";
        if (step == 4) {
            if (index != s.length()) {
                return;
            }
            res.add(nums);
        } else {
            for (int i = index; i < index + 3 && i < s.length(); i++) {
                cur += s.charAt(i);
                int num = Integer.parseInt(cur);
                String temp = nums;
                if (num <= 255 && (cur.length() == 1 || cur.charAt(0) != '0')) {
                    if (step - 3 != 0) {
                        nums += cur + ".";
                    } else {
                        nums += cur;
                    }
                    dfs(s, res, step + 1, i + 1);
                    nums = temp;
                }
            }
        }
    }

    public static ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<>();
        dfs(s, res, 0, 0);
        return res;
    }

}
