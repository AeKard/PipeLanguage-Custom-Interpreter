package frontend.ASTNodeTypes;

public class StringLiteral extends Expr{
    String value;

    public StringLiteral(String value){
        super(NodeType.StringLiteral);
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
    public NodeType getType() {
        return this.kind;
    }
 
    @Override
    public String toString(int indent) {
        String pad = " ".repeat(indent + 4);
        StringBuilder sb = new StringBuilder();

        sb.append(pad).append("kind: StringLiterals\n");
        sb.append(pad).append("value: ").append(this.value);

        return sb.toString();
    }
}
