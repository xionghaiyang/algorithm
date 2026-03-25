package com.sean.leetcode.LeetCodeInterview1707;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-03-25 10:40
 * @Description https://leetcode.cn/problems/baby-names-lcci
 * 面试题 17.07. 婴儿名字
 * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。
 * 有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。
 * 给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。
 * 设计一个算法打印出每个真实名字的实际频率。
 * 注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
 * 在结果列表中，选择 字典序最小 的名字作为真实名字。
 * names.length <= 100000
 */
public class Solution {

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        for (String name : names) {
            int index1 = name.indexOf('('), index2 = name.indexOf(')');
            map.put(name.substring(0, index1), Integer.parseInt(name.substring(index1 + 1, index2)));
        }
        for (String synonym : synonyms) {
            int index = synonym.indexOf(',');
            String name1 = synonym.substring(1, index);
            String name2 = synonym.substring(index + 1, synonym.length() - 1);
            while (parent.containsKey(name1)) {
                name1 = parent.get(name1);
            }
            while (parent.containsKey(name2)) {
                name2 = parent.get(name2);
            }
            if (name1.equals(name2)) {
                continue;
            }
            int freq = map.getOrDefault(name1, 0) + map.getOrDefault(name2, 0);
            String s1 = name1.compareTo(name2) < 0 ? name2 : name1;
            String s2 = name1.compareTo(name2) < 0 ? name1 : name2;
            parent.put(s1, s2);
            map.remove(s1);
            map.put(s2, freq);
        }
        String[] res = new String[map.size()];
        int index = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            res[index++] = new StringBuilder(entry.getKey())
                    .append("(")
                    .append(entry.getValue())
                    .append(")").toString();
        }
        return res;
    }

}
