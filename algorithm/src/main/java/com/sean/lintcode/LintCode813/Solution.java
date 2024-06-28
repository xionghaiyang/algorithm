package com.sean.lintcode.LintCode813;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-01 11:50
 * @Description: https://www.lintcode.com/problem/813/?showListFe=true&page=1&pageSize=50
 * 813 · 找到映射序列
 * 描述
 * 给出了两个A和B的列表，从A映射到B，B是由A的一种回文构词法构成通过随机化A中元素的顺序来实现的。
 * 我们想要找到一个指数映射P，从A到B，映射P[i] = j表示A出现在B中的第i个元素。
 * 这些列表A和B可能包含重复。如果有多个答案，输出任何一个。
 * 输入: A =[12, 28, 46, 32, 50] 和 B =[50, 12, 32, 46, 28]
 * 输出: [1, 4, 3, 2, 0]
 * 解释:
 * P[0] = 1，因为A的第0个元素出现在B[1]， P[1] = 4，因为A的第一个元素出现在B[4]，以此类推。
 */
public class Solution {

    public int[] anagramMappings(int[] a, int[] b) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(b[i])) {
                map.put(b[i], new ArrayList<>());
            }
            map.get(b[i]).add(i);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = map.get(a[i]).get(0);
            map.get(a[i]).remove(0);
        }
        return res;
    }

}
