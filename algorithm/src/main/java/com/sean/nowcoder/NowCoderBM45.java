package com.sean.nowcoder;

import java.util.ArrayList;
import java.util.LinkedList;

public class NowCoderBM45 {

    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (num == null || size == 0 || num.length == 0 || size > num.length) {
            return ans;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && num[stack.peekLast()] < num[i]) {
                stack.pollLast();
            }
            stack.addLast(i);
        }
        for (int i = size; i < num.length; i++) {
            ans.add(num[stack.peekFirst()]);
            while (!stack.isEmpty() && stack.peekFirst() < (i - size + 1)) {
                stack.pollFirst();
            }
            while (!stack.isEmpty() && num[stack.peekLast()] < num[i]) {
                stack.pollLast();
            }
            stack.addLast(i);
        }
        ans.add(num[stack.peekFirst()]);
        return ans;
    }

}
