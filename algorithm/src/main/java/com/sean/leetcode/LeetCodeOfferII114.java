package com.sean.leetcode;

import java.util.*;

public class LeetCodeOfferII114 {

    Map<Character, List<Character>> edges = new HashMap<>();
    boolean valid = true;
    Map<Character, Integer> indegress = new HashMap<>();

    public String alienOrder(String[] words) {
        int length = words.length;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                edges.putIfAbsent(c, new ArrayList<Character>());
            }
        }
        for (int i = 1; i < words.length && valid; i++) {
            addEdge(words[i - 1], words[i]);
        }
        if (!valid) {
            return "";
        }
        Queue<Character> queue = new LinkedList<>();
        for (char character : edges.keySet()) {
            if (!indegress.containsKey(character)) {
                queue.offer(character);
            }
        }
        StringBuffer order = new StringBuffer();
        while (!queue.isEmpty()) {
            char u = queue.poll();
            order.append(u);
            for (char character : edges.get(u)) {
                indegress.put(character, indegress.get(character) - 1);
                if (indegress.get(character) == 0) {
                    queue.offer(character);
                }
            }
        }
        return order.length() == edges.size() ? order.toString() : "";
    }

    public void addEdge(String before, String after) {
        int length = Math.min(before.length(), after.length());
        int index = 0;
        while (index < length) {
            char c1 = before.charAt(index);
            char c2 = after.charAt(index);
            if (c1 != c2) {
                edges.get(c1).add(c2);
                indegress.put(c2, indegress.getOrDefault(c2, 0) + 1);
                break;
            }
            index++;
        }
        if (index == length && before.length() > after.length()) {
            valid = false;
        }
    }

}
