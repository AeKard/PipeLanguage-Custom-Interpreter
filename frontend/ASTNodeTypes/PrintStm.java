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
    public String toString(int indent) {
        String pad = " ".repeat(indent + 2);
        StringBuilder sb = new StringBuilder();

        sb.append("\n" + pad).append("KIND: Print\n");
        sb.append(pad).append("Expressions:\n");
        for (Expr expr : expression) {
            sb.append(expr.toString(indent + 2)).append("\n");
        }

        return sb.toString();
    }
}
