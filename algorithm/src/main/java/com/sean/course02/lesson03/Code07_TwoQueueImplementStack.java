package com.sean.course02.lesson03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author xionghaiyang
 * @Date 2025-04-14 13:22
 * @Description 两个队列实现栈
 */
public class Code07_TwoQueueImplementStack {

    public static class TwoQueueStack<T> {
        private Queue<T> queue;
        private Queue<T> help;

        public TwoQueueStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(T value) {
            queue.offer(value);
        }

        public T poll() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T res = queue.poll();
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return res;
        }

        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T res = queue.poll();
            help.offer(res);
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return res;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("测试开始");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> stack = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!stack.isEmpty()) {
                    System.out.println("出错了");
                    break;
                }
                int num = (int) (max * Math.random());
                myStack.push(num);
                stack.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (max * Math.random());
                    myStack.push(num);
                    stack.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(stack.peek())) {
                        System.out.println("出错了");
                        break;
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(stack.pop())) {
                        System.out.println("出错了");
                        break;
                    }
                } else {
                    if (myStack.isEmpty() != stack.isEmpty()) {
                        System.out.println("出错了");
                        break;
                    }
                }
            }
        }
        System.out.println("测试结束");
    }

}
