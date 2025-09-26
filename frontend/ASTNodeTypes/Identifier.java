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

    public String toString(int indent) {
        if(indent == 2) indent = 0;
        String pad = " ".repeat(indent + 4);
        StringBuilder sb = new StringBuilder();

        sb.append(pad).append("kind: Identifier\n");
        sb.append(pad).append("value: ").append(this.symbol);

        return sb.toString();
    }
}
