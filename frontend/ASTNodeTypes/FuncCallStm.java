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
        String pad = " ".repeat(indent + 2);
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n" + pad).append("KIND: Function Call \n");
        sb.append(pad).append("Callee: " + callee.getValue() + "\n");
        sb.append(pad).append("List of Args: <");
        for(Expr arg : args){
            sb.append(" " + arg.getValue() + " ");
        }
        sb.append(">");

        return sb.toString();
    }
} 