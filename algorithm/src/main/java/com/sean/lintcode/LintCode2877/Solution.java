package com.sean.lintcode.LintCode2877;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @Author xionghaiyang
 * @Date 2022-07-31 08:56
 * @Description https://www.lintcode.com/problem/2877/description?showListFe=true&page=1&pageSize=50
 * 你的代码需要从标准输入流（控制台）中录入多个整数，每个数字之间用空格符号间隔，
 * 以输入数字 0 作为结束输入，将这些数据存储至集合中，
 * 再将集合转为数组，按照元素从小至大的顺序排列好，将排好序的数组转为一个新的集合，
 * 删除集合中最小的元素，将新集合输出。
 */
public class Solution {

    public ArrayList<Integer> sort() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> res = new ArrayList<>();
        int num = sc.nextInt();
        while (num != 0) {
            res.add(num);
            num = sc.nextInt();
        }
        Collections.sort(res);
        res.remove(0);
        return res;
    }

}

