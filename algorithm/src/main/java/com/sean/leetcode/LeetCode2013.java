package com.sean.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode2013 {

    class DetectSquares {
        Map<Integer, Map<Integer, Integer>> row2Col = new HashMap<>();

        public void add(int[] point) {
            int x = point[0], y = point[1];
            Map<Integer, Integer> col2Cnt = row2Col.getOrDefault(x, new HashMap<>());
            col2Cnt.put(y, col2Cnt.getOrDefault(y, 0) + 1);
            row2Col.put(x, col2Cnt);
        }

        public int count(int[] point) {
            int x = point[0], y = point[1];
            int res = 0;
            Map<Integer, Integer> col2Cnt = row2Col.getOrDefault(x, new HashMap<>());
            for (Integer ny : col2Cnt.keySet()) {
                if (ny == y) {
                    continue;
                }
                int c1 = col2Cnt.get(ny);
                int len = y - ny;
                int[] nums = {x + len, x - len};
                for (int nx : nums) {
                    Map<Integer, Integer> temp = row2Col.getOrDefault(nx, new HashMap<>());
                    int c2 = temp.getOrDefault(y, 0);
                    int c3 = temp.getOrDefault(ny, 0);
                    res += c1 * c2 * c3;
                }
            }
            return res;
        }
    }

}
