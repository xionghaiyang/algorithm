package com.sean.base.chapter17;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-02 21:02
 * @Description: TODO
 */
public class Code04_PrintAllPermutations {

    public List<String> permutation1(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        List<Character> rest = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            rest.add(s.charAt(i));
        }
        f(rest, "", res);
        return res;
    }

    private void f(List<Character> rest, String path, List<String> res) {
        if (rest.isEmpty()) {
            res.add(path);
        } else {
            int n = rest.size();
            for (int i = 0; i < n; i++) {
                char cur = rest.get(i);
                rest.remove(i);
                f(rest, path + cur, res);
                rest.add(i, cur);
            }
        }
    }

    public List<String> permutation2(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        char[] str = s.toCharArray();
        g1(str, 0, res);
        return res;
    }

    private void g1(char[] str, int index, List<String> res) {
        if (index == str.length) {
            res.add(String.valueOf(str));
        } else {
            for (int i = index; i < str.length; i++) {
                swap(str, index, i);
                g1(str, index + 1, res);
                swap(str, index, i);
            }
        }
    }

    public List<String> permutation3(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        char[] str = s.toCharArray();
        g2(str, 0, res);
        return res;
    }

    private void g2(char[] str, int index, List<String> res) {
        if (index == str.length) {
            res.add(String.valueOf(str));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, index, i);
                    g2(str, index + 1, res);
                    swap(str, index, i);
                }
            }
        }
    }

    private void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        Code04_PrintAllPermutations solution = new Code04_PrintAllPermutations();
        String s = "acc";
        List<String> res1 = solution.permutation1(s);
        for (String str : res1) {
            System.out.println(str);
        }
        System.out.println("=====================");
        List<String> res2 = solution.permutation2(s);
        for (String str : res2) {
            System.out.println(str);
        }
        System.out.println("=====================");
        List<String> res3 = solution.permutation3(s);
        for (String str : res3) {
            System.out.println(str);
        }
    }

}
