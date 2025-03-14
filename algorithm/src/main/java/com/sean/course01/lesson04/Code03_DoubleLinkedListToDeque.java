package com.sean.course01.lesson04;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author xionghaiyang
 * @Date 2025-03-14 20:32
 * @Description 用双链表结构实现双端队列
 */
public class Code03_DoubleLinkedListToDeque {

    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
            last = null;
            next = null;
        }
    }

    public static class MyDeque<T> {
        private Node<T> head;
        private Node<T> tail;
        private int size;

        public MyDeque() {
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

        public void pushHead(T value) {
            Node<T> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
            size++;
        }

        public void pushTail(T value) {
            Node<T> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
            size++;
        }

        public T pollHead() {
            T res = null;
            if (head == null) {
                return res;
            }
            size--;
            res = head.value;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
            }
            return res;
        }

        public T pollTail() {
            T res = null;
            if (head == null) {
                return res;
            }
            size--;
            res = tail.value;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
            }
            return res;
        }

        public T peekHead() {
            T res = null;
            if (head != null) {
                res = head.value;
            }
            return res;
        }

        public T peekTail() {
            T res = null;
            if (tail != null) {
                res = tail.value;
            }
            return res;
        }
    }

    public static void testDeque() {
        MyDeque<Integer> myDeque = new MyDeque<>();
        Deque<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 20000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            if (myDeque.isEmpty() != test.isEmpty()) {
                System.out.println("出错了");
            }
            if (myDeque.size != test.size()) {
                System.out.println("出错了");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (maxValue * Math.random());
                if (Math.random() < 0.5) {
                    myDeque.pushHead(num);
                    test.addFirst(num);
                } else {
                    myDeque.pushTail(num);
                    test.addLast(num);
                }
            } else if (decide < 0.66) {
                if (!myDeque.isEmpty()) {
                    int num1 = 0;
                    int num2 = 0;
                    if (Math.random() < 0.5) {
                        num1 = myDeque.pollHead();
                        num2 = test.pollFirst();
                    } else {
                        num1 = myDeque.pollTail();
                        num2 = test.pollLast();
                    }
                    if (num1 != num2) {
                        System.out.println("出错了");
                    }
                }
            } else {
                if (!myDeque.isEmpty()) {
                    int num1 = 0;
                    int num2 = 0;
                    if (Math.random() < 0.5) {
                        num1 = myDeque.peekHead();
                        num2 = test.peekFirst();
                    } else {
                        num1 = myDeque.peekTail();
                        num2 = test.peekLast();
                    }
                    if (num1 != num2) {
                        System.out.println("出错了");
                    }
                }
            }
            if (myDeque.size() != test.size()) {
                System.out.println("出错了");
            }
            while (!myDeque.isEmpty()) {
                int num1 = myDeque.pollHead();
                int num2 = test.pollFirst();
                if (num1 != num2) {
                    System.out.println("出错了");
                }
            }
        }
        System.out.println("测试结束");
    }

    public static void main(String[] args) {
        testDeque();
    }

}
