package com.sean.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Fizz Buzz
 * https://leetcode-cn.com/problems/fizz-buzz/
 */
public class LeetCode412 {

    //模拟法
    public static List<String> fizzBuzz1(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            boolean divisibleBy3 = i % 3 == 0;
            boolean divisibleBy5 = i % 5 == 0;
            if (divisibleBy3 && divisibleBy5) {
                res.add("FizzBuzz");
            } else if (divisibleBy3) {
                res.add("Fizz");
            } else if (divisibleBy5) {
                res.add("Buzz");
            } else {
                res.add(Integer.toString(i));
            }
        }
        return res;
    }

    //字符串拼接
    public List<String> fizzBuzz2(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            boolean divisibleBy3 = i % 3 == 0;
            boolean divisibleBy5 = i % 5 == 0;
            String resStr = "";
            if (divisibleBy3) {
                resStr += "Fizz";
            }
            if (divisibleBy5) {
                resStr += "Buzz";
            }
            if ("".equals(resStr)) {
                resStr += Integer.toString(i);
            }
            res.add(resStr);
        }
        return res;
    }

    //用散列表
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        HashMap<Integer, String> fizzBizzDict = new HashMap<>();
        fizzBizzDict.put(3, "Fizz");
        fizzBizzDict.put(5, "Buzz");
        for (int i = 1; i <= n; i++) {
            String resStr = "";
            for (Integer key : fizzBizzDict.keySet()) {
                if(i %key == 0){
                    resStr+=fizzBizzDict.get(key);
                }
            }
            if("".equals(resStr)){
                resStr+=Integer.toString(i);
            }
            res.add(resStr);
        }
        return res;
    }

}
