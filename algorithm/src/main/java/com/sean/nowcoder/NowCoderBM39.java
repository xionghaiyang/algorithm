package com.sean.nowcoder;

public class NowCoderBM39 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void serializeProcess(TreeNode root, StringBuilder res) {
        if (root == null) {
            res.append('#');
            return;
        }
        res.append(root.val).append('!');
        serializeProcess(root.left, res);
        serializeProcess(root.right, res);
    }

    public static String Serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        StringBuilder res = new StringBuilder();
        serializeProcess(root, res);
        return res.toString();
    }

    public static int index = 0;

    public static TreeNode deserializeProcess(String str) {
        if (str.charAt(index) == '#') {
            index++;
            return null;
        }
        int val = 0;
        while (str.charAt(index) != '!' && index != str.length()) {
            val = val * 10 + (str.charAt(index) - '0');
            index++;
        }
        TreeNode root = new TreeNode(val);
        if (index == str.length()) {
            return root;
        } else {
            index++;
        }
        root.left = deserializeProcess(str);
        root.right = deserializeProcess(str);
        return root;
    }

    public static TreeNode Deserialize(String str) {
        if (str.equals("#")) {
            return null;
        }
        TreeNode res = deserializeProcess(str);
        return res;
    }

}
