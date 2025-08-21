package frontend.ASTNodeTypes;

public class NumericalLiteral extends Expr { // Inherit from expression
    float symbol;
    public NumericalLiteral(float symbol){
        super(NodeType.NumericalLiteral);
        this.symbol = symbol;
    }

    @Override
    public String toString(){
        return "\n{ NumericalLiteral : ("+ Float.toString(symbol) +")}";
    }
}
