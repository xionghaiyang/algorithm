package com.sean.base.chapter11;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-26 19:56
 * @Description: TODO
 */
public class Code05_TreeMaxWidth {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }

    }

    public int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        Map<Node, Integer> levelMaP = new HashMap<>();
        levelMaP.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMaP.get(cur);
            if (cur.left != null) {
                levelMaP.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMaP.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        //当前层，最右节点是谁
        Node curEnd = head;
        //下一层，最右节点是谁
        Node nextEnd = null;
        int max = 0;
        //当前层的节点数
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    public Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        boolean success = true;
        System.out.println("test begin!");
        Code05_TreeMaxWidth solution = new Code05_TreeMaxWidth();
        for (int i = 0; i < testTimes; i++) {
            Node head = solution.generateRandomBST(maxLevel, maxValue);
            if (solution.maxWidthUseMap(head) != solution.maxWidthNoMap(head)) {
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Oops!");
        System.out.println("test finish!");
    }

}
