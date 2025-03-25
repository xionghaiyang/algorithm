package com.sean.course01.lesson07;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-03-25 21:15
 * @Description https://leetcode.cn/problems/path-sum-ii
 * 收集达标路径和
 */
public class Code04_PathSumII {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }
        process(root, 0, sum);
        return res;
    }

    private void process(TreeNode x, int preSum, int sum) {
        if (x.left == null && x.right == null) {
            if (preSum + x.val == sum) {
                path.add(x.val);
                res.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
            return;
        }
        path.add(x.val);
        preSum += x.val;
        if (x.left != null) {
            process(x.left, preSum, sum);
        }
        if (x.right != null) {
            process(x.right, preSum, sum);
        }
        path.remove(path.size() - 1);
    }

}
