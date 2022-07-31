package com.sean.leetcode;

public class LeetCode641 {

    class MyCircularDeque {

        private int capacity;
        private int[] arr;
        private int front;
        private int rear;

        public MyCircularDeque(int k) {
            capacity = k + 1;
            arr = new int[capacity];
            //头部指向第一个存放元素的位置
            //插入时，先减，再赋值
            //删除时，索引+1（注意取模）
            front = 0;
            //尾部指向下一个插入元素的位置
            //插入时，先赋值，再加
            //删除时，索引-1（注意取模）
            rear = 0;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            front = (front - 1 + capacity) % capacity;
            arr[front] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            arr[rear] = value;
            rear = (rear + 1) % capacity;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % capacity;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            rear = (rear - 1 + capacity) % capacity;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return arr[front];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return arr[(rear - 1 + capacity) % capacity];
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public boolean isFull() {
            return (rear + 1) % capacity == front;
        }
    }
}
