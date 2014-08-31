package com.leetcode.binarytreedepth;

import com.leetcode.common.TreeNode;

/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class BinaryTreeSolution {
	
	public int maxDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    if (left > right) {
        return left + 1;
    } else {
        return right + 1;
    }
}}
