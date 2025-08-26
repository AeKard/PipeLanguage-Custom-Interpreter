package frontend.ASTNodeTypes;

public abstract class Expr extends Stms{ // Inherit from Statement
    Expr(NodeType kind){
        super(kind);
    }

    public abstract NodeType getType();
    public abstract String getValue();
}
