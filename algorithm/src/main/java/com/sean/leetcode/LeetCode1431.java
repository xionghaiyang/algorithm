package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 拥有最多糖果的孩子
 * https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/
 */
public class LeetCode1431 {

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        ArrayList<Boolean> res = new ArrayList<>(candies.length);
        int max = candies[0];
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] > max) {
                max = candies[i];
            }
        }
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return res;
    }

    private static void printList(List<Boolean> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                System.out.print(list.get(i) + ",");
            } else {
                System.out.print(list.get(i));
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        printList(kidsWithCandies(new int[]{2, 3, 5, 1, 3},3));
        printList(kidsWithCandies(new int[]{4, 2, 1, 1, 2},1));
    }

}
