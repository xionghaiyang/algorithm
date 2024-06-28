package com.sean.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 找出数组中的幸运数
 * https://leetcode-cn.com/problems/find-lucky-integer-in-an-array/
 */
public class LeetCode1394 {

    public static int findLucky(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Iterator<Map.Entry<Integer, Integer>> entryIterator = map.entrySet().iterator();
        int res = -1;
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, Integer> next = entryIterator.next();
            Integer key = next.getKey();
            Integer value = next.getValue();
            if (key == value && key > res) {
                res = key;
            }
        }
        return res;
    }

}
