package com.sean.course02.lesson03;

/**
 * @Author xionghaiyang
 * @Date 2025-04-11 21:13
 * @Description 用数组实现队列
 */
public class Code04_RingArray {

    public class MyQueue {
        private int[] arr;
        private int offerIndex;
        private int pollIndex;
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            offerIndex = 0;
            pollIndex = 0;
            size = 0;
            this.limit = limit;
        }

        public void offer(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            arr[offerIndex] = value;
            offerIndex = nextIndex(offerIndex);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列空了，不能再拿了");
            }
            size--;
            int res = arr[pollIndex];
            pollIndex = nextIndex(pollIndex);
            return res;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }

}
