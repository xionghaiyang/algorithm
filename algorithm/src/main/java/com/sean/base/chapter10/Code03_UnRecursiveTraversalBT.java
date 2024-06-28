package com.sean.base.chapter10;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-25 20:36
 * @Description: 二叉树前序、中序和后序遍历的非递归版本
 */
public class Code03_UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public void pre(Node head) {
        System.out.print("pre-order:");
        if (head != null) {
            Deque<Node> stack = new ArrayDeque<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    public void in(Node head) {
        System.out.print("in-order:");
        if (head != null) {
            Deque<Node> stack = new ArrayDeque<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    public void pos1(Node head) {
        System.out.print("pos-order:");
        if (head != null) {
            Deque<Node> stack1 = new ArrayDeque<>();
            Deque<Node> stack2 = new ArrayDeque<>();
            stack1.push(head);
            while (!stack1.isEmpty()) {
                head = stack1.pop(); //头 右 左
                stack2.push(head);
                if (head.left != null) {
                    stack1.push(head.left);
                }
                if (head.right != null) {
                    stack1.push(head.right);
                }
            }
            // 左 右 头
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().value + " ");
            }
        }
        System.out.println();
    }

    public void pos2(Node head) {
        System.out.print("pos-order:");
        if (head != null) {
            Deque<Node> stack = new ArrayDeque<>();
            stack.push(head);
            Node cur = null;
            while (!stack.isEmpty()) {
                cur = stack.peek();
                if (cur.left != null && head != cur.left && head != cur.right) {
                    stack.push(cur.left);
                } else if (cur.right != null && head != cur.right) {
                    stack.push(cur.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = cur;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        Code03_UnRecursiveTraversalBT solution = new Code03_UnRecursiveTraversalBT();
        solution.pre(head);
        solution.in(head);
        solution.pos1(head);
        solution.pos2(head);
    }

}
