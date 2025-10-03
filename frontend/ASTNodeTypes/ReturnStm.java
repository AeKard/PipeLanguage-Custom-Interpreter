package frontend.ASTNodeTypes;

public class ReturnStm extends Stms{
    final Expr value;

    public ReturnStm(Expr value){
        super(NodeType.ReturnStm);
        this.value = value;
    }
    public Expr getValue(){
        return this.value;
    }
    @Override
    public String toString(int indent){
        String pad = " ".repeat(indent + 4);
        String childPad = " ".repeat(indent + 6);
        StringBuilder sb = new StringBuilder();

        sb.append("\n"+pad).append("KIND: Return Stm \n");
        sb.append(pad).append("value: \n").append((this.value != null) ? this.value.toString(indent + 4) : sb.append(childPad).append("Return type null"));

        return sb.toString();
    }
}