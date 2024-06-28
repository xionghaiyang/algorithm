package com.sean.leetcode.LeetCode1023;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-14 09:09
 * @Description: https://leetcode.cn/problems/camelcase-matching/
 * 1023. 驼峰式匹配
 * 如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。
 * （我们可以在任何位置插入每个字符，也可以插入 0 个字符。）
 * 给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。
 * 只有在待查项 queries[i] 与模式串 pattern 匹配时， answer[i] 才为 true，否则为 false。
 */
public class Solution {

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if (queries == null || queries.length == 0) {
            return new ArrayList<>();
        }
        int n = queries.length;
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            int p = 0;
            for (int j = 0; j < queries[i].length(); j++) {
                char c = queries[i].charAt(j);
                if (p < pattern.length() && pattern.charAt(p) == c) {
                    p++;
                } else if (Character.isUpperCase(c)) {
                    flag = false;
                    break;
                }
            }
            if (p < pattern.length()) {
                flag = false;
            }
            res.add(flag);
        }
        return res;
    }

}
