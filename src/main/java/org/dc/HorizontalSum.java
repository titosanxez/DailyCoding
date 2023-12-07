package org.dc;

import org.dc.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HorizontalSum {

    static int[] computeSums(TreeNode node) {

        List<Integer> result = computeSums(
                Arrays.asList(node)
        );

        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    private static  ArrayList<Integer> computeSums(
            List<TreeNode> nodes
    ) {

        var result = new ArrayList<Integer>();
        if (nodes.isEmpty()) {
            return result;
        }

        var children = new ArrayList<TreeNode>(nodes.size() * 2);
        int sum = 0;
        for (var node : nodes) {
            sum+= node.value;
            if (node.left != null) {
                children.add(node.left);
            }
            if (node.right != null) {
                children.add(node.right);
            }
        }

        result.add(sum);
        // compute next level
        var childrenResult = computeSums(children);
        result.addAll(childrenResult);

        return result;
    }
}
