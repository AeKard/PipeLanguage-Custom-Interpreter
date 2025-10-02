package frontend.ASTNodeTypes;

import java.util.List;
public class FuncCallStm extends Expr{
    Expr callee;
    List<Expr> args;

    public FuncCallStm(Expr callee, List<Expr> args){
        super(NodeType.FunctionCall);
        this.callee = callee;
        this.args = args;
    }

    public NodeType getType(){return this.kind;}
    public String getValue(){return "Function call does not return a value";}

    public Expr getCallee(){return this.callee;}
    public List<Expr> getArgs(){return this.args;}
    @Override
    public String toString(int indent){
        return "Funcall";
    }
} 