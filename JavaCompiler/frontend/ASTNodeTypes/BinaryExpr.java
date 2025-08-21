package frontend.ASTNodeTypes;

public class BinaryExpr extends Expr { // Inherit from expression
    Expr left;
    Expr right;
    String op;

    BinaryExpr(Expr left, Expr right, String op){
        super(NodeType.BinaryExpr);
        this.left = left;
        this.right = right;
        this.op = op;
    }
}