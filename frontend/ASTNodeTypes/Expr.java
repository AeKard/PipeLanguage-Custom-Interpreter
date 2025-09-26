package frontend.ASTNodeTypes;

public abstract class Expr extends Stms{ // Inherit from Statement
    protected Expr(NodeType kind){
        super(kind);
    }

    public abstract NodeType getType();
    public abstract String getValue();
    // public abstract int indentBlock();
}
