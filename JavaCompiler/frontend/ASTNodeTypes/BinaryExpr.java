package frontend.ASTNodeTypes;

public class BinaryExpr extends Expr { // Inherit from expression
    Expr left;
    Expr right;
    String op;

    public BinaryExpr(Expr left, Expr right, String op){
        super(NodeType.BinaryExpr);
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public String toString() {
        return toString(0); // start at indent 0
    }

    private String toString(int indent) {
        String pad = "  ".repeat(indent);       // 2 spaces per indent
        String childPad = "  ".repeat(indent+1);

        StringBuilder sb = new StringBuilder();
        sb.append(pad).append("{\n");
        sb.append(childPad).append("kind: BinaryExpr,\n");

        sb.append(childPad).append("left:\n");
        if (left instanceof BinaryExpr)
            sb.append(((BinaryExpr) left).toString(indent+2)).append("\n");
        else
            sb.append("  ".repeat(indent+2)).append(left).append("\n");

        sb.append(childPad).append("right:\n");
        if (right instanceof BinaryExpr)
            sb.append(((BinaryExpr) right).toString(indent+2)).append("\n");
        else
            sb.append("  ".repeat(indent+2)).append(right).append("\n");

        sb.append(childPad).append("Operator: { value: ").append(op).append(" }\n");
        sb.append(pad).append("}");
        return sb.toString();
    }
}