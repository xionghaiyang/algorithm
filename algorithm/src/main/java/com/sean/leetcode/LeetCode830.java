package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 较大分组的位置
 * https://leetcode-cn.com/problems/positions-of-large-groups/
 */
public class LeetCode830 {

    public static List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        int start = 0, end = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[start] == chars[i]) {
                end++;
            } else {
                if (end - start + 1 >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(end);
                    res.add(list);
                }
                start = i;
                end = i;
            }
        }
        if (end - start + 1 >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(end);
            res.add(list);
        }
        return res;
    }

    private static void printList(List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            List<Integer> integerList = list.get(i);
            integerList.forEach(s -> System.out.print(s + " "));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printList(largeGroupPositions("abbxxxxzzy"));
        System.out.println("==========================================");
        printList(largeGroupPositions("abc"));
        System.out.println("==========================================");
        printList(largeGroupPositions("abcdddeeeeaabbbcd"));
        System.out.println("==========================================");
        printList(largeGroupPositions("aba"));
    }

}
