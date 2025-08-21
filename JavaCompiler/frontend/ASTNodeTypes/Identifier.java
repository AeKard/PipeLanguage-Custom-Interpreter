package frontend.ASTNodeTypes;

public class Identifier extends Expr { // Inherit from expression
    String symbol;
    public Identifier(String symbol){
        super(NodeType.Identifier);
        this.symbol = symbol;
    }

    @Override
    public String toString(){
        return "\n{ Identifier : ("+ this.symbol +") }";
    }
}
