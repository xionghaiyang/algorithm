package com.sean.leetcode;

import java.util.LinkedList;

/**
 * 无法吃午餐的学生数量
 * https://leetcode-cn.com/problems/number-of-students-unable-to-eat-lunch/
 */
public class LeetCode1700 {

    public static int countStudents(int[] students, int[] sandwiches) {
        if (students == null || students.length == 0) {
            return 0;
        }
        if (sandwiches == null || sandwiches.length == 0) {
            return students.length;
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int student : students) {
            linkedList.addLast(student);
        }
        for (int i = 0; i < sandwiches.length; i++) {
            if (linkedList.isEmpty()) {
                return 0;
            }
            int size = linkedList.size();
            boolean flag = false;
            for (int j = 0; j < size; j++) {
                Integer first = linkedList.removeFirst();
                if (first == sandwiches[i]) {
                    flag = true;
                    break;
                } else {
                    linkedList.addLast(first);
                }
            }
            if (!flag) {
                return size;
            }
        }
        return linkedList.size();
    }

    public static void main(String[] args) {
        System.out.println(countStudents(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1}));
        System.out.println(countStudents(new int[]{1, 1, 1, 0, 0, 1}, new int[]{1, 0, 0, 0, 1, 1}));
    }

}
