package com.sean.leetcode.LeetCode1096;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-07 08:08
 * @Description: https://leetcode.cn/problems/brace-expansion-ii/
 * 1096. 花括号展开 II
 * 如果你熟悉 Shell 编程，那么一定了解过花括号展开，它可以用来生成任意字符串。
 * 花括号展开的表达式可以看作一个由 花括号、逗号 和 小写英文字母 组成的字符串，定义下面几条语法规则：
 * 如果只给出单一的元素 x，那么表达式表示的字符串就只有 "x"。R(x) = {x}
 * 例如，表达式 "a" 表示字符串 "a"。
 * 而表达式 "w" 就表示字符串 "w"。
 * 当两个或多个表达式并列，以逗号分隔，我们取这些表达式中元素的并集。R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
 * 例如，表达式 "{a,b,c}" 表示字符串 "a","b","c"。
 * 而表达式 "{{a,b},{b,c}}" 也可以表示字符串 "a","b","c"。
 * 要是两个或多个表达式相接，中间没有隔开时，我们从这些表达式中各取一个元素依次连接形成字符串。R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}
 * 例如，表达式 "{a,b}{c,d}" 表示字符串 "ac","ad","bc","bd"。
 * 表达式之间允许嵌套，单一元素与表达式的连接也是允许的。
 * 例如，表达式 "a{b,c,d}" 表示字符串 "ab","ac","ad"​​​​​​。
 * 例如，表达式 "a{b,c}{d,e}f{g,h}" 可以表示字符串 "abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"。
 * 给出表示基于给定语法规则的表达式 expression，返回它所表示的所有字符串组成的有序列表。
 * <p>
 * 假如你希望以「集合」的概念了解此题，也可以通过点击 “显示英文描述” 获取详情。
 */
public class Solution {

    String expression;
    int index;

    public List<String> braceExpansionII1(String expression) {
        this.expression = expression;
        this.index = 0;
        Set<String> set = expr();
        return new ArrayList<>(set);
    }

    // expr . term | term, expr
    private Set<String> expr() {
        Set<String> res = new TreeSet<>();
        while (true) {
            //与term()求解结果求并集
            res.addAll(term());
            //如果匹配到逗号则继续，否则结束匹配
            if (index < expression.length() && expression.charAt(index) == ',') {
                index++;
            } else {
                break;
            }
        }
        return res;
    }

    // term . item | item term
    private Set<String> term() {
        //初始化空集合，与之后的求解结果求笛卡尔积
        Set<String> res = new TreeSet<String>() {{
            add("");
        }};
        //item的开头是{或小写字母，只有符合时才继续匹配
        while (index < expression.length() && (expression.charAt(index) == '{' || Character.isLetter(expression.charAt(index)))) {
            Set<String> sub = item();
            Set<String> tmp = new TreeSet<>();
            for (String left : res) {
                for (String right : sub) {
                    tmp.add(left + right);
                }
            }
            res = tmp;
        }
        return res;
    }

    // item . letter | { expr }
    private Set<String> item() {
        Set<String> res = new TreeSet<>();
        if (expression.charAt(index) == '{') {
            index++;
            res = expr();
        } else {
            res.add(String.valueOf(expression.charAt(index)));
        }
        index++;
        return res;
    }

    public List<String> braceExpansionII(String expression) {
        Deque<Character> op = new ArrayDeque<>();
        List<Set<String>> stk = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ',') {
                //不断地弹出运算符，知道栈为空或者栈顶不为乘号
                while (!op.isEmpty() && op.peek() == '*') {
                    ope(op, stk);
                }
                op.push('+');
            } else if (expression.charAt(i) == '{') {
                //首先判断是否需要添加乘号，再将{添加到运算符栈中
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    op.push('*');
                }
                op.push('{');
            } else if (expression.charAt(i) == '}') {
                //不断地弹出栈顶运算符，直到栈顶为{
                while (!op.isEmpty() && op.peek() != '{') {
                    ope(op, stk);
                }
                op.pop();
            } else {
                //首先判断是否需要添加乘号，再将新构造的集合添加到集合栈中
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    op.push('*');
                }
                StringBuilder sb = new StringBuilder();
                sb.append(expression.charAt(i));
                stk.add(new TreeSet<String>() {{
                    add(sb.toString());
                }});
            }
        }
        while (!op.isEmpty()) {
            ope(op, stk);
        }
        return new ArrayList<String>(stk.get(stk.size() - 1));
    }

    //弹出栈顶运算符，并进行计算
    private void ope(Deque<Character> op, List<Set<String>> stk) {
        int l = stk.size() - 2, r = stk.size() - 1;
        if (op.peek() == '+') {
            stk.get(l).addAll(stk.get(r));
        } else {
            Set<String> tmp = new TreeSet<>();
            for (String left : stk.get(l)) {
                for (String right : stk.get(r)) {
                    tmp.add(left + right);
                }
            }
            stk.set(l, tmp);
        }
        op.pop();
        stk.remove(stk.size() - 1);
    }

}
