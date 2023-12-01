package org.dc.common;


public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        var printer = new Printer();
        Visitor.preorder(this, printer);
        return printer.toString();
    }

    public interface Visitor {
        void visit(TreeNode node);

        static void preorder(TreeNode node, Visitor visitor) {
            visitor.visit(node);
            if (node == null) {
                return;
            }

            if (node.left != null) {
                preorder(node.left, visitor);
            } else {
                preorder(null, visitor);
            }

            if (node.right != null) {
                preorder(node.right, visitor);
            } else {
                preorder(null, visitor);
            }
        }
    }

    public static class Printer implements Visitor {
        static final String NULL_VALUE = "null";

        StringBuilder output = new StringBuilder();

        @Override
        public void visit(TreeNode node) {
            if (!output.isEmpty()) {
                output.append(",");
            }
            if (node != null) {
                output.append(node.value);
            } else {
                output.append(NULL_VALUE);
            }
        }

        @Override
        public String toString() {
            return output.toString();
        }
    }
}