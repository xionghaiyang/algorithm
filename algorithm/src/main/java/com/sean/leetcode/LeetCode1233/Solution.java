package com.sean.leetcode.LeetCode1233;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-08 09:14
 * @Description: https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem/
 * 1233. 删除子文件夹
 * 你是一位系统管理员，手里有一份文件夹列表 folder，你的任务是要删除该列表中的所有 子文件夹，并以 任意顺序 返回剩下的文件夹。
 * 如果文件夹 folder[i] 位于另一个文件夹 folder[j] 下，那么 folder[i] 就是 folder[j] 的 子文件夹 。
 * 文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：'/' 后跟一个或者多个小写英文字母。
 * 例如，"/leetcode" 和 "/leetcode/problems" 都是有效的路径，而空字符串和 "/" 不是。
 */
public class Solution {

    class Trie {
        int ref;
        Map<String, Trie> children;

        public Trie() {
            ref = -1;
            children = new HashMap<>();
        }
    }

    public List<String> removeSubfolders(String[] folder) {
        if (folder == null || folder.length == 0) {
            return new ArrayList<>();
        }
        int n = folder.length;
        Trie root = new Trie();
        for (int i = 0; i < n; i++) {
            List<String> path = split(folder[i]);
            Trie cur = root;
            for (String name : path) {
                cur.children.putIfAbsent(name, new Trie());
                cur = cur.children.get(name);
            }
            cur.ref = i;
        }
        List<String> res = new ArrayList<>();
        dfs(folder, res, root);
        return res;
    }

    private List<String> split(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '/') {
                res.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(c);
            }
        }
        res.add(cur.toString());
        return res;
    }

    private void dfs(String[] folder, List<String> res, Trie cur) {
        if (cur.ref != -1) {
            res.add(folder[cur.ref]);
            return;
        }
        for (Trie child : cur.children.values()) {
            dfs(folder, res, child);
        }
    }

}
