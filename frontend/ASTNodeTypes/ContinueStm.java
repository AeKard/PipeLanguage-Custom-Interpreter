package frontend.ASTNodeTypes;

public class ContinueStm extends Stms{
    public ContinueStm(){
        super(NodeType.ContinueStm);
    }
    @Override
    public String toString(int indent){
        String pad = " ".repeat(indent + 2);
        StringBuilder sb = new StringBuilder();

        sb.append("\n" + pad).append("KIND: Continue \n");
        sb.append(pad).append("Value: skip current loop");

        return sb.toString();
    }
}
