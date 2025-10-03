package frontend.ASTNodeTypes;

public class BreakStm extends Stms{
    public BreakStm(){
        super(NodeType.BreakStm);
    }
    @Override
    public String toString(int indent){
        String pad = " ".repeat(indent + 2);
        StringBuilder sb = new StringBuilder();

        sb.append("\n" + pad).append("KIND: Break \n");
        sb.append(pad).append("Value: Jump out of loop");

        return sb.toString();
    }

}
