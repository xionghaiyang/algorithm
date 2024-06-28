package com.sean.nowcoder;

import java.util.HashSet;
import java.util.Set;

public class NowCoderBM53 {

    public static int minNumberDisappeared(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 1;
        while (set.contains(ans)) {
            ans++;
        }
        return ans;
    }

}
