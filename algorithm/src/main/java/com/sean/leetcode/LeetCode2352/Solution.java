package com.sean.leetcode.LeetCode2352;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-06 08:18
 * @Description: https://leetcode.cn/problems/equal-row-and-column-pairs/
 * 2352. 相等行列对
 * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
 * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
 */
public class Solution {

    public int equalPairs(int[][] grid) {
        int m = grid.length;
        StringBuilder stringBuilder = new StringBuilder();
        String str;
        Map<String, Integer> map1 = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                stringBuilder.append(grid[i][j]).append(",");
            }
            str = stringBuilder.toString();
            map1.put(str, map1.getOrDefault(str, 0) + 1);
            stringBuilder.setLength(0);
        }
        Map<String, Integer> map2 = new HashMap<>();
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < m; i++) {
                stringBuilder.append(grid[i][j]).append(",");
            }
            str = stringBuilder.toString();
            map2.put(str, map2.getOrDefault(str, 0) + 1);
            stringBuilder.setLength(0);
        }
        int res = 0;
        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                res += map1.get(key) * map2.get(key);
            }
        }
        return res;
    }

}
