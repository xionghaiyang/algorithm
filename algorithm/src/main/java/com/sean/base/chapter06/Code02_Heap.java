package com.sean.base.chapter06;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-19 19:59
 * @Description: 堆结构
 * 1）堆结构就是用数组实现的完全二叉树结构
 * 2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
 * 3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
 * 4）堆结构的heapInsert与heapify操作
 * 5）堆结构的增大和减少
 * 6）优先级队列结构，就是堆结构
 */
public class Code02_Heap {

    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            int res = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return res;
        }

        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int lagrgest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                lagrgest = arr[lagrgest] > arr[index] ? lagrgest : index;
                if (lagrgest == index) {
                    break;
                }
                swap(arr, lagrgest, index);
                index = lagrgest;
                left = index * 2 + 1;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int res = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return res;
        }

    }

    public static void main(String[] args) {
        //大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        heap.add(5);
        System.out.println(heap.peek());
        heap.add(5);
        System.out.println(heap.peek());
        heap.add(3);
        System.out.println(heap.peek());
        heap.add(7);
        System.out.println(heap.peek());
        heap.add(0);
        System.out.println(heap.peek());
        System.out.println("====================");
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
        System.out.println("====================");
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            int curLimt = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimt);
            RightMaxHeap test = new RightMaxHeap(curLimt);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("test end!");
    }

}
