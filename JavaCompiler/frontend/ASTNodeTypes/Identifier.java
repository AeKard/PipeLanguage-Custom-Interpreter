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
        return this.symbol;
    }

    @Override
    public String toString(int indent){
        String pad = " ".repeat(indent * 2);
        return pad + "{ kind : \"Identifier\", value: "+ this.symbol +" }";
    }
}
