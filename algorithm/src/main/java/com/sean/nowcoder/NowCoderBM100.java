package com.sean.nowcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NowCoderBM100 {

    public class Solution {

        public class Node {
            int key, val;
            Node prev, next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private Map<Integer, Node> map = new HashMap<>();
        private Node head = new Node(-1, -1);
        private Node tail = new Node(-1, -1);
        private int k = 0;

        public int[] LRU(int[][] operators, int k) {
            this.k = k;
            head.next = tail;
            tail.prev = head;
            int len = (int) Arrays.stream(operators)
                    .filter(x -> x[0] == 2)
                    .count();
            int[] res = new int[len];
            for (int i = 0, j = 0; i < operators.length; i++) {
                if (operators[i][0] == 1) {
                    set(operators[i][1], operators[i][2]);
                } else {
                    res[j++] = get(operators[i][1]);
                }
            }
            return res;
        }

        public Solution(int capacity) {
            // write code here
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.prev.next = node.next;
                node.next.prev = node.prev;
                moveToHead(node);
                return node.val;
            }
            return -1;
        }

        public void set(int key, int val) {
            if (get(key) > -1) {
                map.get(k).val = val;
            } else {
                if (map.size() == k) {
                    int rk = tail.prev.key;
                    tail.prev.prev.next = tail;
                    tail.prev = tail.prev.prev;
                    map.remove(rk);
                }
                Node node = new Node(key, val);
                map.put(key, node);
                moveToHead(node);
            }
        }

        public void moveToHead(Node node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }
    }

}
