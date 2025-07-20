package com.sean.leetcode.LeetCode1948;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-07-20 05:20
 * @Description https://leetcode.cn/problems/delete-duplicate-folders-in-system
 * 1948. 删除系统中的重复文件夹
 * 由于一个漏洞，文件系统中存在许多重复文件夹。
 * 给你一个二维数组 paths，其中 paths[i] 是一个表示文件系统中第 i 个文件夹的绝对路径的数组。
 * 例如，["one", "two", "three"] 表示路径 "/one/two/three" 。
 * 如果两个文件夹（不需要在同一层级）包含 非空且相同的 子文件夹 集合 并具有相同的子文件夹结构，则认为这两个文件夹是相同文件夹。
 * 相同文件夹的根层级 不 需要相同。
 * 如果存在两个（或两个以上）相同 文件夹，则需要将这些文件夹和所有它们的子文件夹 标记 为待删除。
 * 例如，下面文件结构中的文件夹 "/a" 和 "/b" 相同。
 * 它们（以及它们的子文件夹）应该被 全部 标记为待删除：
 * /a
 * /a/x
 * /a/x/y
 * /a/z
 * /b
 * /b/x
 * /b/x/y
 * /b/z
 * 然而，如果文件结构中还包含路径 "/b/w" ，那么文件夹 "/a" 和 "/b" 就不相同。
 * 注意，即便添加了新的文件夹 "/b/w" ，仍然认为 "/a/x" 和 "/b/x" 相同。
 * 一旦所有的相同文件夹和它们的子文件夹都被标记为待删除，文件系统将会 删除 所有上述文件夹。
 * 文件系统只会执行一次删除操作。
 * 执行完这一次删除操作后，不会删除新出现的相同文件夹。
 * 返回二维数组 ans ，该数组包含删除所有标记文件夹之后剩余文件夹的路径。
 * 路径可以按 任意顺序 返回。
 * 1 <= paths.length <= 2 * 10^4
 * 1 <= paths[i].length <= 500
 * 1 <= paths[i][j].length <= 10
 * 1 <= sum(paths[i][j].length) <= 2 * 10^5
 * path[i][j] 由小写英文字母组成
 * 不会存在两个路径都指向同一个文件夹的情况
 * 对于不在根层级的任意文件夹，其父文件夹也会包含在输入中
 */
public class Solution {

    public class TrieNode {
        private String name;
        private Map<String, TrieNode> children;
        private boolean deleted;

        public TrieNode(String name) {
            this.name = name;
            children = new HashMap<>();
            deleted = false;
        }

        public TrieNode add(String name) {
            if (!children.containsKey(name)) {
                children.put(name, new TrieNode(name));
            }
            return children.get(name);
        }
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        TrieNode root = new TrieNode("/");
        for (List<String> path : paths) {
            TrieNode cur = root;
            for (String name : path) {
                cur = cur.add(name);
            }
        }
        Map<String, TrieNode> expr2Node = new HashMap<>();
        for (TrieNode child : root.children.values()) {
            getExpr(child, expr2Node);
        }
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        for (TrieNode child : root.children.values()) {
            dfs(child, path, res);
        }
        return res;
    }

    private String getExpr(TrieNode node, Map<String, TrieNode> expr2Node) {
        if (node.children.isEmpty()) {
            return node.name;
        }
        List<String> expr = new ArrayList<>();
        for (TrieNode child : node.children.values()) {
            expr.add("(" + getExpr(child, expr2Node) + ")");
        }
        Collections.sort(expr);
        String subTreeExpr = String.join("", expr);
        TrieNode n = expr2Node.get(subTreeExpr);
        if (n != null) {
            n.deleted = true;
            node.deleted = true;
        } else {
            expr2Node.put(subTreeExpr, node);
        }
        return node.name + subTreeExpr;
    }

    private void dfs(TrieNode node, List<String> path, List<List<String>> res) {
        if (node.deleted) {
            return;
        }
        path.add(node.name);
        res.add(new ArrayList<>(path));
        for (TrieNode child : node.children.values()) {
            dfs(child, path, res);
        }
        path.remove(path.size() - 1);
    }

}
