package frontend.ASTNodeTypes;

public abstract class Expr extends Stms{ // Inherit from Statement
    Expr(NodeType kind){
        super(kind);
    }
}
