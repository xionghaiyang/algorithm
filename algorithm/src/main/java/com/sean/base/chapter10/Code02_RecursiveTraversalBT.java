package com.sean.base.chapter10;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-25 20:26
 * @Description: 二叉数的前序、中序和后序遍历
 * 先序：任何子树的处理顺序都是，先头节点、再左子树、然后右子树
 * 中序：任何子树的处理顺序都是，先左子树、再头节点、然后右子树
 * 后序：任何子树的处理顺序都是，先左子树、再右子树、然后头节点
 * 1）理解递归序
 * 2）先序、中序、后序都可以在递归序的基础上加工出来
 * 3）第一次到达一个节点就打印就是先序、第二次打印即中序、第三次即后序
 */
public class Code02_RecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public void f(Node head) {
        if (head == null) {
            return;
        }
        //1
        f(head.left);
        //2
        f(head.right);
        //
    }

    //先序打印所有节点
    public void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    //中序打印所有节点
    public void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    //后续打印所有节点
    public void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        Code02_RecursiveTraversalBT solution = new Code02_RecursiveTraversalBT();
        solution.pre(head);
        System.out.println("============");
        solution.in(head);
        System.out.println("============");
        solution.pos(head);
    }

}
