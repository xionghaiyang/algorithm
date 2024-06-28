package com.sean.leetcode.LeetCode970;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-04 16:36
 * @Description: https://leetcode.cn/problems/powerful-integers/
 * 970. 强整数
 * 给定三个整数 x 、 y 和 bound ，返回 值小于或等于 bound 的所有 强整数 组成的列表 。
 * 如果某一整数可以表示为 x^i + y^j ，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个 强整数 。
 * 你可以按 任何顺序 返回答案。在你的回答中，每个值 最多 出现一次。
 */
public class Solution {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        int xi = 1;
        for (int i = 0; i < 21; i++) {
            int yj = 1;
            for (int j = 0; j < 21; j++) {
                int value = xi + yj;
                if (value <= bound) {
                    set.add(value);
                } else {
                    break;
                }
                yj *= y;
            }
            if (xi > bound) {
                break;
            }
            xi *= x;
        }
        return new ArrayList<>(set);
    }

}
