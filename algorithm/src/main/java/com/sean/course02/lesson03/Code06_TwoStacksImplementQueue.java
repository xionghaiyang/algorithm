package com.sean.course02.lesson03;

import java.util.Stack;

/**
 * @Author xionghaiyang
 * @Date 2025-04-14 12:43
 * @Description 两个栈实现队列
 */
public class Code06_TwoStacksImplementQueue {

    public static class TwoStacksQueue {
        private Stack<Integer> stackOffer;
        private Stack<Integer> stackPoll;

        public TwoStacksQueue() {
            stackOffer = new Stack<>();
            stackPoll = new Stack<>();
        }

        public void offer(int pushInt) {
            stackOffer.push(pushInt);
            offerToPoll();
        }

        private void offerToPoll() {
            if (stackPoll.isEmpty()) {
                while (!stackOffer.isEmpty()) {
                    stackPoll.push(stackOffer.pop());
                }
            }
        }

        public int poll() {
            if (stackPoll.isEmpty() && stackOffer.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            offerToPoll();
            return stackPoll.pop();
        }

        public int peek() {
            if (stackPoll.isEmpty() && stackOffer.isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }
            offerToPoll();
            return stackPoll.peek();
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.offer(1);
        test.offer(2);
        test.offer(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }

}
