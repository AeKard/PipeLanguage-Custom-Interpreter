package frontend.ASTNodeTypes;

// Binary Expression 

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
        if(indent == 4) indent = 6;// I add this to patch the spacing in AST NODE XD
        String pad = " ".repeat(indent + 2);
        String childPad = " ".repeat(indent + 4 * 2);
        StringBuilder sb = new StringBuilder();

        sb.append( pad).append("KIND: BinaryExpr\n");

        sb.append(pad).append("  left:\n");
        sb.append(left.toString(indent + 4)).append("\n");

        sb.append(pad).append("  right:\n");
        sb.append(right.toString(indent + 4)).append("\n");

        sb.append(pad).append("  operator:\n");
        sb.append(childPad).append("value: ").append(this.op);

        return sb.toString();
    }

}