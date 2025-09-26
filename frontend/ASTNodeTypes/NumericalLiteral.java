package frontend.ASTNodeTypes;


public class NumericalLiteral extends Expr { // Inherit from expression
    String symbol;
    public NumericalLiteral(String symbol){
        super(NodeType.NumericalLiteral);
        this.symbol = symbol;
    }

    public NodeType getType(){
        return this.kind;
    }
    
    public String getValue(){
        return symbol;
    }

    @Override
    public String toString(int indent) {
        if(indent == 2) indent = 0;
        String pad = " ".repeat(indent + 4);
        StringBuilder sb = new StringBuilder();

        sb.append(pad).append("kind: NumericalLiteral\n");
        sb.append(pad).append("value: ").append(this.symbol);

        return sb.toString();
    }
}
