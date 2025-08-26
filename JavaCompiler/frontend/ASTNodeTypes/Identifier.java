package frontend.ASTNodeTypes;

public class Identifier extends Expr { // Inherit from expression
    String symbol;
    public Identifier(String symbol){
        super(NodeType.Identifier);
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
        return pad + "\t{ kind : \"Identifier\", value: "+ this.symbol +" }";
    }
}
