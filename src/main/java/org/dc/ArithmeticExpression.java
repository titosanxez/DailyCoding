package org.dc;

import java.util.*;


class ArithmeticExpression {
    static final List<Operator> OPERATOR_SYMBOLS = List.of(
            Operator.PLUS,
            Operator.MINUS,
            Operator.DIVIDE,
            Operator.PRODUCT
    );
    static final Map<Operator, ApplyOperator> operators = Map.of(
            Operator.PLUS,
            (l, r) -> (l + r),
            Operator.MINUS,
            (l, r) -> (l - r),
            Operator.DIVIDE,
            (l, r) -> (l / r),
            Operator.PRODUCT,
            (l, r) -> (l * r)
    );

    private final Node node;

    public ArithmeticExpression(String expression) {
        this.node = parse(expression);
    }

    public Double evaluate() {
        return evaluate(this.node);
    }

    private Node parse(String expression) {
        for (var operator : OPERATOR_SYMBOLS) {
            var cursor = new Cursor(expression);
            while (cursor.hasNext()) {
                if (cursor.get() == '(') {
                    // find counterpart ')'
                    var startBracketIndex = cursor.index;
                    int closingBracketIndex = indexOfClosingBracket(
                            cursor.increment()
                    );
                    String subExpression = expression.substring(
                            startBracketIndex + 1,
                            closingBracketIndex
                    ).trim();
                    var subExpNode = parse(subExpression);
                    var leftBranch = expression.substring(0, startBracketIndex).trim();
                    var rightBranch = expression.substring(closingBracketIndex +1).trim();
                    if (!leftBranch.isEmpty()) {
                        var operatorNode = parse(leftBranch);
                        operatorNode.right = subExpNode;
                        subExpNode = operatorNode;
                    }
                    if (!rightBranch.isEmpty()) {
                        var operatorNode = parse(rightBranch);
                        operatorNode.left = subExpNode;
                        subExpNode = operatorNode;
                    }
                    return subExpNode;
                }

                if (operator.symbol.equals(cursor.get().toString())) {
                    var node = new Node(operator);
                    var leftBranch = expression.substring(0, cursor.index).trim();
                    if (!leftBranch.isEmpty()) {
                        node.left = parse(leftBranch);
                    }
                    var rightBranch = expression.substring(cursor.index + 1).trim();
                    if (!rightBranch.isEmpty()) {
                        node.right = parse(rightBranch);
                    }
                    return node;
                }

                cursor.increment();
            }
        }

        return new Node(Double.parseDouble(expression.trim()));
    }

    private static int indexOfClosingBracket(Cursor cursor) {
        int openCount = 1;
        while (cursor.hasNext()) {
            if (cursor.get() == ')') {
                --openCount;
            } else if (cursor.get() == '('){
                ++openCount;
            }
            if (openCount == 0) {
                return cursor.index;
            }
            cursor.increment();
        }

        throw new IllegalArgumentException("Invalid expression, missing closing ')'");
    }

    private static Double evaluate(Node node) {
        if (node.operator == Operator.VALUE) {
            if (node.value.isEmpty()) {
                throw new IllegalStateException("value not present");
            }
            return node.value.get();
        }

        Double leftValue = 0.;
        if (node.left != null) {
            leftValue = evaluate(node.left);
        }
        Double rightValue = 0.;
        if (node.right != null) {
            rightValue = evaluate(node.right);
        }

        return operators.get(node.operator).apply(leftValue, rightValue);
    }

    private static class Cursor {
        int index;
        String input;

        public Cursor(String input) {
            this.index = 0;
            this.input = input;
        }

        public Cursor increment() {
            this.index++;
            return this;
        }

        public boolean hasNext() {
            return (this.index < this.input.length());
        }

        public Character get() {
            return this.input.charAt(this.index);
        }
    }

    private static class Node {
        Operator operator;
        Node left;
        Node right;
        Optional<Double> value;

        public Node(Operator operator) {
            this.operator = operator;
        }

        public Node(Double value) {
            this.operator = Operator.VALUE;
            this.value = Optional.of(value);
        }
    }

    private enum Operator {
        PLUS("+"),
        MINUS("-"),
        PRODUCT("*"),
        DIVIDE("/"),
        VALUE(null);

        final String symbol;

        Operator(String symbol) {
            this.symbol = symbol;
        }
    }

    interface ApplyOperator {
        double apply(double left, double right);
    }
}
