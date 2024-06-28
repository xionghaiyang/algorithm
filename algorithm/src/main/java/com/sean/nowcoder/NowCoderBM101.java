package com.sean.nowcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class NowCoderBM101 {

    public class Node {
        int freq;
        int key;
        int val;

        public Node(int freq, int key, int val) {
            this.freq = freq;
            this.key = key;
            this.val = val;
        }
    }

    private Map<Integer, LinkedList<Node>> freq_mp = new HashMap<>();
    private Map<Integer, Node> mp = new HashMap<>();
    private int size = 0;
    private int min_freq = 0;

    public int[] LFU(int[][] operators, int k) {
        this.size = k;
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

    private void set(int key, int value) {
        if (mp.containsKey(key)) {
            update(mp.get(key), key, value);
        } else {
            if (size == 0) {
                int oldKey = freq_mp.get(min_freq)
                        .getLast()
                        .key;
                freq_mp.get(min_freq).removeLast();
                if (freq_mp.get(min_freq).isEmpty()) {
                    freq_mp.remove(min_freq);
                }
                mp.remove(oldKey);
            } else {
                size--;
            }
            min_freq = 1;
            if (!freq_mp.containsKey(1)) {
                freq_mp.put(1, new LinkedList<Node>());
            }
            freq_mp.get(1).addFirst(new Node(1, key, value));
            mp.put(key, freq_mp.get(1).getFirst());
        }
    }

    private void update(Node node, int key, int value) {
        int freq = node.freq;
        freq_mp.get(freq).remove(node);
        if (freq_mp.get(freq).isEmpty()) {
            freq_mp.remove(freq);
            if (min_freq == freq) {
                min_freq++;
            }
        }
        if (!freq_mp.containsKey(freq + 1)) {
            freq_mp.put(freq + 1, new LinkedList<Node>());
        }
        freq_mp.get(freq + 1)
                .addFirst(new Node(freq + 1, key, value));
        mp.put(key, freq_mp.get(freq + 1).getFirst());
    }

    private int get(int key) {
        int res = -1;
        if (mp.containsKey(key)) {
            Node node = mp.get(key);
            res = node.val;
            update(node, key, res);
        }
        return res;
    }

}
