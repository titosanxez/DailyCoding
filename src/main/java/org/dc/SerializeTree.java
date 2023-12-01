package org.dc;

import org.dc.common.Serializer;
import org.dc.common.TreeNode;

public class SerializeTree {

    public static class StringSerializer implements Serializer<TreeNode, String> {
        static final String NULL_VALUE = "null";
        TreeNode.Printer nodePrinter = new TreeNode.Printer();
        int cursor = 0;

        public String serialize(TreeNode node) {
            TreeNode.Visitor.preorder(node, nodePrinter);
            return nodePrinter.toString();
        }

        public TreeNode deserialize(String tree) {
            var rootNode = this.readNextNode(tree);
            if (rootNode != null) {
                this.deserialize(tree, rootNode);
            }
            return rootNode;
        }

        private void deserialize(String tree, TreeNode current) {
            if (this.cursor >= tree.length() || current == null) {
                return;
            }
            // Traverse left
            var node = this.readNextNode(tree);
            if (node != null) {
                current.left = node;
                this.deserialize(tree, node);
            }

            // Traverse right
            node = this.readNextNode(tree);
            if (node != null) {
                current.right = node;
                this.deserialize(tree, node);
            }
        }

        private TreeNode readNextNode(String tree) {
            var separatorIndex = tree.indexOf(",", this.cursor);
            if (separatorIndex == -1) {
                separatorIndex = tree.length();
            }
            var valueString =  tree.substring(cursor, separatorIndex);
            this.cursor = separatorIndex + 1;
            return valueString.equals(StringSerializer.NULL_VALUE)
                    ? null
                    : new TreeNode(Integer.parseInt(valueString));
        }
    }

}
