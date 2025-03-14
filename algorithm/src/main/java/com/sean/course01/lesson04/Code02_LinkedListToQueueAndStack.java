package com.sean.course01.lesson04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author xionghaiyang
 * @Date 2025-03-14 19:08
 * @Description 用单链表实现队列和栈
 */
public class Code02_LinkedListToQueueAndStack {

    public static class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
            next = null;
        }
    }

    public static class MyQueue<T> {
        private Node<T> head;
        private Node<T> tail;
        private int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offer(T value) {
            Node<T> cur = new Node<>(value);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        public T poll() {
            T res = null;
            if (head != null) {
                res = head.value;
                head = head.next;
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return res;
        }

        public T peek() {
            T res = null;
            if (head != null) {
                res = head.value;
            }
            return res;
        }
    }

    public static class MyStack<T> {
        private Node<T> head;
        private int size;

        public MyStack() {
            head = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(T value) {
            Node<T> cur = new Node<>(value);
            if (head == null) {
                head = cur;
            } else {
                cur.next = head;
                head = cur;
            }
            size++;
        }

        public T pop() {
            T res = null;
            if (head != null) {
                res = head.value;
                head = head.next;
                size--;
            }
            return res;
        }

        public T peek() {
            return head != null ? head.value : null;
        }
    }

    private static void testQueue() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            if (myQueue.isEmpty() != test.isEmpty()) {
                System.out.println("出错了");
            }
            if (myQueue.size() != test.size()) {
                System.out.println("出错了");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (maxValue * Math.random());
                myQueue.offer(num);
                test.offer(num);
            } else if (decide < 0.66) {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.poll();
                    int num2 = test.poll();
                    if (num1 != num2) {
                        System.out.println("出错了");
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("出错了");
                    }
                }
            }
            if (myQueue.size != test.size()) {
                System.out.println("出错了");
            }
            while (!myQueue.isEmpty()) {
                int num1 = myQueue.poll();
                int num2 = test.poll();
                if (num1 != num2) {
                    System.out.println("出错了");
                }
            }
        }
        System.out.println("测试结束");
    }

    private static void testStack() {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty() != test.isEmpty()) {
                System.out.println("出错了");
            }
            if (myStack.size() != test.size()) {
                System.out.println("出错了");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (maxValue * Math.random());
                myStack.push(num);
                test.push(num);
            } else if (decide < 0.66) {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.pop();
                    int num2 = test.pop();
                    if (num1 != num2) {
                        System.out.println("出错了");
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("出错了");
                    }
                }
            }
        }
        if (myStack.size() != test.size()) {
            System.out.println("出错了");
        }
        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = test.pop();
            if (num1 != num2) {
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");
    }

    public static void main(String[] args) {
        testQueue();
        testStack();
    }

}
