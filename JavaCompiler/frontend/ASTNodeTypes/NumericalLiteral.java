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
    public String toString(int indent){
        String pad = " ".repeat(indent);
        return pad + "\t{ kind: \"NumericalLiteral\", value: "+ symbol +" }";
    }
}
