package frontend.ASTNodeTypes;

import java.util.List;

public class PrintStm extends Stms{
     List<Expr> expression;

     public PrintStm(List<Expr>expression){
        super(NodeType.Print);
        this.expression = expression;
    }

    public NodeType getType(){
        return this.kind;
    }
    
    public List<Expr> getValue(){
        return this.expression;
    }

    @Override
    public String toString(int indent){
        String pad = " ".repeat(indent);
        return pad + "Type: { "+ this.kind + "} Value:{ \"List to be printed\" }";
    }
}
