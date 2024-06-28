package com.sean.lintcode.LintCode56;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2022-08-27 11:39
 * @Description https://www.lintcode.com/problem/56/?showListFe=true&page=1&pageSize=50
 * 56 · 两数之和
 * 描述
 * 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
 * 你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 0 到 n-1。
 */
public class Solution {

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int key = target - numbers[i];
            if (map.containsKey(key)) {
                return new int[]{map.get(key), i};
            }
            map.put(numbers[i], i);
        }
        return null;
    }

}
