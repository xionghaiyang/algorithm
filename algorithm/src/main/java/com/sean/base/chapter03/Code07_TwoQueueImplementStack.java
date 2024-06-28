package com.sean.base.chapter03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author xionghaiyang
 * @Date 2022-08-28 11:53
 * @Description 用两个队列实现栈
 */
public class Code07_TwoQueueImplementStack {

    public static class TwoQueueStack<T> {
        public Queue<T> queue;
        public Queue<T> help;

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
            Queue<T> temp = this.queue;
            queue = help;
            help = temp;
            return res;
        }

        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T res = queue.poll();
            help.offer(res);
            Queue<T> temp = this.queue;
            queue = help;
            help = temp;
            return res;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public static void main(String[] args) {
        System.out.println("test begin!");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("出错了");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("出错了");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("出错了");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("出错了");
                    }
                }
            }
        }
        System.out.println("test finish!");
    }

}
