package com.sean.leetcode.LeetCode599;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-01 13:44
 * @Description: https://leetcode.cn/problems/minimum-index-sum-of-two-lists/description/
 * 599. 两个列表的最小索引总和
 * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。
 * 如果答案不止一个，则输出所有答案并且不考虑顺序。
 * 你可以假设答案总是存在。
 */
public class Solution {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        List<String> res = new ArrayList<>();
        int indexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int j = map.get(list2[i]);
                if (i + j < indexSum) {
                    res.clear();
                    res.add(list2[i]);
                    indexSum = i + j;
                } else if (i + j == indexSum) {
                    res.add(list2[i]);
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }

}
