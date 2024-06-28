package com.sean.leetcode;

import java.util.*;

public class LeetCode736 {

    Map<String, Deque<Integer>> scope = new HashMap<>();
    int index = 0;

    public int evaluate(String expression) {
        return innerEvaluate(expression);
    }

    private int innerEvaluate(String expression) {
        if (expression.charAt(index) != '(') {//非表达式，可能为整数或变量
            if (Character.isLowerCase(expression.charAt(index))) {//变量
                String var = parseVar(expression);
                return scope.get(var).peek();
            } else {//整数
                return parseInt(expression);
            }
        }
        int res;
        index++;//移除左括号
        if (expression.charAt(index) == 'l') {//let表达式
            index += 4;//移除let
            List<String> vars = new ArrayList<>();
            while (true) {
                if (!Character.isLowerCase(expression.charAt(index))) {
                    res = innerEvaluate(expression); //let表达式的最后一个expr表达式的值
                    break;
                }
                String var = parseVar(expression);
                if (expression.charAt(index) == ')') {
                    res = scope.get(var).peek();//let表达式的最后一个expr表达式的值
                    break;
                }
                vars.add(var);
                index++;//移除空格
                int e = innerEvaluate(expression);
                scope.putIfAbsent(var, new ArrayDeque<Integer>());
                scope.get(var).push(e);
                index++;//移除空格
            }
            for (String var : vars) {
                scope.get(var).pop();//清除当前作用域的变量
            }
        } else if (expression.charAt(index) == 'a') {//add表达式
            index += 4;//移除add
            int e1 = innerEvaluate(expression);
            index++;//移除空格
            int e2 = innerEvaluate(expression);
            res = e1 + e2;
        } else {//mult表达式
            index += 5;//移除mult
            int e1 = innerEvaluate(expression);
            index++;
            int e2 = innerEvaluate(expression);
            res = e1 * e2;
        }
        index++;
        return res;
    }

    // 解析变量
    private String parseVar(String expression) {
        int length = expression.length();
        StringBuffer res = new StringBuffer();
        while (index < length && expression.charAt(index) != ' ' && expression.charAt(index) != ')') {
            res.append(expression.charAt(index));
            index++;
        }
        return res.toString();
    }

    //解析整数
    private int parseInt(String expression) {
        int length = expression.length();
        int res = 0, sign = 1;
        if (expression.charAt(index) == '-') {
            sign = -1;
            index++;
        }
        while (index < length && Character.isDigit(expression.charAt(index))) {
            res = res * 10 + (expression.charAt(index) - '0');
            index++;
        }
        return sign * res;
    }

}
