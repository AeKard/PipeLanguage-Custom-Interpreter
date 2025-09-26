package frontend.ASTNodeTypes;

import java.util.ArrayList;


public class Program extends Stms{
    ArrayList<Stms> body; 
    
    public Program(ArrayList<Stms> body){
        super(NodeType.Program);
        this.body = body;
    } 

    public NodeType getKind(){
        return super.kind;
    }

    public ArrayList<Stms> getBody(){
        return body;
    }

    @Override
    public String toString(int indent) {
        String pad = " ".repeat(indent + 2);
        StringBuilder sb = new StringBuilder();
        sb.append(pad).append("Program:");

        for (Stms stm : body) {
            sb.append(stm.toString(indent + 2)).append("\n");
        }
        return sb.toString();
    }
}

