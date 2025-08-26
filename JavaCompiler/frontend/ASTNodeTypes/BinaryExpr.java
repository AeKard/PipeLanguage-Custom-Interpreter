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

    public NodeType getType(){return this.kind;}
    public String getValue(){return this.op;}
    public Expr getLeft(){return this.left;}
    public Expr getRight(){return this.right;}

    @Override
    public String toString(int indent) {
        String pad = "  ".repeat(indent);
        String childPad = "  ".repeat(indent + 1);

        StringBuilder sb = new StringBuilder();
        sb.append(pad).append("{\n");
        sb.append(childPad).append("kind: BinaryExpr,\n");

        sb.append(childPad).append("left:\n");
        sb.append(left.toString(indent + 2)).append("\n");

        sb.append(childPad).append("right:\n");
        sb.append(right.toString(indent + 2)).append("\n");

        sb.append(childPad).append("Operator: { value: ").append(op).append(" }\n");
        sb.append(pad).append("}");
        return sb.toString();
    }
}