package com.sean.leetcode.LeetCode2306;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2024-09-25 20:32
 * @Description https://leetcode.cn/problems/naming-a-company/
 * 2306. 公司命名
 * 给你一个字符串数组 ideas 表示在公司命名过程中使用的名字列表。
 * 公司命名流程如下：
 * 从 ideas 中选择 2 个 不同 名字，称为 ideaA 和 ideaB 。
 * 交换 ideaA 和 ideaB 的首字母。
 * 如果得到的两个新名字 都 不在 ideas 中，那么 ideaA ideaB（串联 ideaA 和 ideaB ，中间用一个空格分隔）是一个有效的公司名字。
 * 否则，不是一个有效的名字。
 * 返回 不同 且有效的公司名字的数目。
 * 2 <= ideas.length <= 5 * 10^4
 * 1 <= ideas[i].length <= 10
 * ideas[i] 由小写英文字母组成
 * ideas 中的所有字符串 互不相同
 */
public class Solution {

    public long distinctNames(String[] ideas) {
        Map<Character, Set<String>> map = new HashMap<>();
        for (String idea : ideas) {
            map.putIfAbsent(idea.charAt(0), new HashSet<>());
            map.get(idea.charAt(0)).add(idea.substring(1));
        }
        long res = 0;
        for (Map.Entry<Character, Set<String>> entryA : map.entrySet()) {
            Character preA = entryA.getKey();
            Set<String> setA = entryA.getValue();
            for (Map.Entry<Character, Set<String>> entryB : map.entrySet()) {
                Character preB = entryB.getKey();
                Set<String> setB = entryB.getValue();
                if (preA == preB) {
                    continue;
                }
                int intersectSize = getIntersectSize(setA, setB);
                res += (long) (setA.size() - intersectSize) * (setB.size() - intersectSize);
            }
        }
        return res;
    }

    private int getIntersectSize(Set<String> setA, Set<String> setB) {
        int res = 0;
        for (String s : setA) {
            if (setB.contains(s)) {
                res++;
            }
        }
        return res;
    }

}
