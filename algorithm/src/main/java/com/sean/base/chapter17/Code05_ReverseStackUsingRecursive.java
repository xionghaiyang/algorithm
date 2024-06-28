package com.sean.base.chapter17;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-02 22:19
 * @Description: TODO
 */
public class Code05_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    //栈底元素移除掉
    //上面的元素盖下来
    //返回移除掉的栈底元素
    public static int f(Stack<Integer> stack) {
        int res = stack.pop();
        if (stack.isEmpty()) {
            return res;
        } else {
            int last = f(stack);
            stack.push(res);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
