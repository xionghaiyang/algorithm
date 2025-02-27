package com.sean.leetcode.LeetCode2296;

/**
 * @Author xionghaiyang
 * @Date 2025-02-27 07:35
 * @Description https://leetcode.cn/problems/design-a-text-editor/
 * 2296. 设计一个文本编辑器
 * 请你设计一个带光标的文本编辑器，它可以实现以下功能：
 * 添加：在光标所在处添加文本。
 * 删除：在光标所在处删除文本（模拟键盘的删除键）。
 * 移动：将光标往左或者往右移动。
 * 当删除文本时，只有光标左边的字符会被删除。
 * 光标会留在文本内，也就是说任意时候 0 <= cursor.position <= currentText.length 都成立。
 * 请你实现 TextEditor 类：
 * TextEditor() 用空文本初始化对象。
 * void addText(string text) 将 text 添加到光标所在位置。添加完后光标在 text 的右边。
 * int deleteText(int k) 删除光标左边 k 个字符。返回实际删除的字符数目。
 * string cursorLeft(int k) 将光标向左移动 k 次。返回移动后光标左边 min(10, len) 个字符，其中 len 是光标左边的字符数目。
 * string cursorRight(int k) 将光标向右移动 k 次。返回移动后光标左边 min(10, len) 个字符，其中 len 是光标左边的字符数目。
 */
public class TextEditor {

    class Node {
        char val;
        Node prev;
        Node next;

        public Node(char val) {
            this.val = val;
        }

        private void insert(char val) {
            Node node = new Node(val);
            node.next = this;
            node.prev = this.prev;
            if (this.prev != null) {
                this.prev.next = node;
            }
            this.prev = node;
        }

        private void remove() {
            Node node = this.prev;
            this.prev = node.prev;
            if (node.prev != null) {
                node.prev.next = this;
            }
        }

        private String range(Node end) {
            StringBuilder res = new StringBuilder();
            Node node = this;
            while (node != end) {
                res.append(node.val);
                node = node.next;
            }
            return res.toString();
        }

    }

    private Node cur;

    public TextEditor() {
        cur = new Node('\0');//空字符\0即是光标所在位置
    }

    //将 text 添加到光标所在位置。添加完后光标在 text 的右边。
    public void addText(String text) {
        for (char val : text.toCharArray()) {
            cur.insert(val);
        }
    }

    //删除光标左边 k 个字符。返回实际删除的字符数目。
    public int deleteText(int k) {
        int res = 0;
        while (k > 0 && cur.prev != null) {
            cur.remove();
            k--;
            res++;
        }
        return res;
    }

    //将光标向左移动 k 次。返回移动后光标左边 min(10, len) 个字符，其中 len 是光标左边的字符数目。
    public String cursorLeft(int k) {
        while (k > 0 && cur.prev != null) {
            cur = cur.prev;
            k--;
        }
        Node head = cur;
        for (int i = 0; i < 10 && head.prev != null; i++) {
            head = head.prev;
        }
        return head.range(cur);
    }

    //将光标向右移动 k 次。返回移动后光标左边 min(10, len) 个字符，其中 len 是光标左边的字符数目。
    public String cursorRight(int k) {
        while (k > 0 && cur.next != null) {
            cur = cur.next;
            k--;
        }
        Node head = cur;
        for (int i = 0; i < 10 && head.prev != null; i++) {
            head = head.prev;
        }
        return head.range(cur);
    }

}
