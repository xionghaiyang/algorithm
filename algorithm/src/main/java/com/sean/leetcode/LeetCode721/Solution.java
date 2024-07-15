package com.sean.leetcode.LeetCode721;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-07-15 07:25
 * @Description https://leetcode.cn/problems/accounts-merge/
 * 721. 账户合并
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * 现在，我们想合并这些账户。
 * 如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
 * 请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
 * 一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是 按字符 ASCII 顺序排列 的邮箱地址。
 * 账户本身可以以 任意顺序 返回。
 */
public class Solution {

    class UnionFind {
        int[] parent;
        int[] size;
        int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int i, int j) {
            int fi = find(i);
            int fj = find(j);
            if (fi != fj) {
                if (size[fi] >= size[fj]) {
                    parent[fj] = parent[fi];
                    size[fi] += size[fj];
                } else {
                    parent[fi] = parent[fj];
                    size[fj] += size[fi];
                }
            }
        }

        public int find(int i) {
            int index = 0;
            while (i != parent[i]) {
                help[index++] = i;
                i = parent[i];
            }
            for (index--; index >= 0; index--) {
                parent[help[index]] = i;
            }
            return i;
        }

    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> email2Index = new HashMap<>();
        Map<String, String> email2Name = new HashMap<>();
        int emailsCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!email2Index.containsKey(email)) {
                    email2Index.put(email, emailsCount++);
                    email2Name.put(email, name);
                }
            }
        }
        UnionFind unionFind = new UnionFind(emailsCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = email2Index.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = email2Index.get(nextEmail);
                unionFind.union(firstIndex, nextIndex);
            }
        }
        Map<Integer, List<String>> index2Emails = new HashMap<>();
        for (String email : email2Index.keySet()) {
            int index = unionFind.find(email2Index.get(email));
            List<String> account = index2Emails.getOrDefault(index, new ArrayList<>());
            account.add(email);
            index2Emails.put(index, account);
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> emails : index2Emails.values()) {
            Collections.sort(emails);
            String name = email2Name.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);
            res.add(account);
        }
        return res;
    }

}
