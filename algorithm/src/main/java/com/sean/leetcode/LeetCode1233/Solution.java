package com.sean.leetcode.LeetCode1233;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-08 09:14
 * @Description: https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem
 * 1233. 删除子文件夹
 * 你是一位系统管理员，手里有一份文件夹列表 folder，你的任务是要删除该列表中的所有 子文件夹，并以 任意顺序 返回剩下的文件夹。
 * 如果文件夹 folder[i] 位于另一个文件夹 folder[j] 下，那么 folder[i] 就是 folder[j] 的 子文件夹 。
 * 文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：'/' 后跟一个或者多个小写英文字母。
 * 例如，"/leetcode" 和 "/leetcode/problems" 都是有效的路径，而空字符串和 "/" 不是。
 * 1 <= folder.length <= 4 * 10^4
 * 2 <= folder[i].length <= 100
 * folder[i] 只包含小写字母和 '/'
 * folder[i] 总是以字符 '/' 起始
 * folder 每个元素都是 唯一 的
 */
public class Solution {

    public class Trie {
        private Map<String, Trie> children;
        private int fid;

        public Trie() {
            children = new HashMap<>();
            fid = -1;
        }

        public void insert(int fid, String f) {
            Trie node = this;
            String[] paths = f.split("/");
            for (int i = 1; i < paths.length; i++) {
                String path = paths[i];
                if (!node.children.containsKey(path)) {
                    node.children.put(path, new Trie());
                }
                node = node.children.get(path);
            }
            node.fid = fid;
        }

        public List<Integer> search() {
            List<Integer> res = new ArrayList<>();
            dfs(this, res);
            return res;
        }

        private void dfs(Trie root, List<Integer> res) {
            if (root.fid != -1) {
                res.add(root.fid);
                return;
            }
            for (Trie child : root.children.values()) {
                dfs(child, res);
            }
        }
    }

    public List<String> removeSubfolders(String[] folder) {
        Trie trie = new Trie();
        int n = folder.length;
        for (int i = 0; i < n; i++) {
            trie.insert(i, folder[i]);
        }
        List<String> res = new ArrayList<>();
        for (int fid : trie.search()) {
            res.add(folder[fid]);
        }
        return res;
    }

}
