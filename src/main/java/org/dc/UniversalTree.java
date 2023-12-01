package org.dc;

import org.dc.common.TreeNode;

public class UniversalTree {

    private record CheckResult(
            int value, int depth
    ) {

    }

    static int check(TreeNode node) {
        return checkResult(node).value;
    }

    static private CheckResult checkResult(TreeNode node) {
        if (node == null) {
            return new CheckResult(0, 0);
        }

        CheckResult leftResult = null;
        if (node.left != null) {
            leftResult = checkResult(node.left);
        }

        CheckResult rightResult = null;
        if (node.right != null) {
            rightResult = checkResult(node.right);
        }

        boolean isLeaf = (node.right == null && node.left == null);
        if (isLeaf) {
            return new CheckResult(1, 0);
        }

        int leftValue = node.left != null ? node.left.value : node.right.value;
        int rightValue = node.right != null ? node.right.value : node.left.value;
        boolean isUniversal = (node.value == leftValue) && (node.value == rightValue);
        int universalDepth = 1;
        int count = 0;
        int leftTreeUniversalDepth = 0;
        int rightTreeUniversalDepth = 0;
        if (leftResult != null) {
            count += leftResult.value;
            leftTreeUniversalDepth = leftResult.depth;

        }
        if (rightResult != null) {
            count += rightResult.value;
            rightTreeUniversalDepth = rightResult.depth;
        }
        if (isUniversal) {
            ++count;
            if (leftTreeUniversalDepth == rightTreeUniversalDepth
                    && leftTreeUniversalDepth != 0) {
                universalDepth = leftTreeUniversalDepth + 1;
                ++count;
            }
        }

        return new CheckResult(count, universalDepth);
    }
}
