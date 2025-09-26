package frontend.ASTNodeTypes;

import java.util.ArrayList;
// di this
public class BlockStms extends Stms{
    public ArrayList<Stms> body;

    public BlockStms(ArrayList<Stms> body){
        super(NodeType.Block);
        this.body = body;
    }   

    
    @Override
    public String toString(int indent){
        String pad = " ".repeat(indent + 4);
        String childPad = " ".repeat(indent + 4 + 2);
        StringBuilder sb = new StringBuilder();
        
        sb.append(pad).append("Kind: Body\n");
        sb.append(pad).append("value:\n");
        for (Stms stm : body) {
            sb.append(childPad).append(stm.toString(indent + 4));
        }
        return sb.toString();
    }
}
