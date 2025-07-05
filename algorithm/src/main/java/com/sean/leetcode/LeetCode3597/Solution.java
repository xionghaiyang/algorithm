package com.sean.leetcode.LeetCode3597;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-07-05 15:25
 * @Description https://leetcode.cn/problems/partition-string
 * 3597. 分割字符串
 * 给你一个字符串 s，按照以下步骤将其分割为 互不相同的段 ：
 * 从下标 0 开始构建一个段。
 * 逐字符扩展当前段，直到该段之前未曾出现过。
 * 只要当前段是唯一的，就将其加入段列表，标记为已经出现过，并从下一个下标开始构建新的段。
 * 重复上述步骤，直到处理完整个字符串 s。
 * 返回字符串数组 segments，其中 segments[i] 表示创建的第 i 段。
 * 1 <= s.length <= 10^5
 * s 仅包含小写英文字母。
 */
public class Solution {

    public List<String> partitionString(String s) {
        int n = s.length();
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append(s.charAt(i));
            if (!set.contains(str.toString())) {
                set.add(str.toString());
                res.add(str.toString());
                str.setLength(0);
            }
        }
        return res;
    }

    public class Node {
        Node[] children;

        public Node() {
            children = new Node[26];
        }
    }

    public List<String> partitionString1(String s) {
        int n = s.length();
        List<String> res = new ArrayList<>();
        Node root = new Node();
        Node cur = root;
        for (int i = 0, left = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new Node();
                res.add(s.substring(left, i + 1));
                left = i + 1;
                cur = root;
            } else {
                cur = cur.children[index];
            }
        }
        return res;
    }

}
