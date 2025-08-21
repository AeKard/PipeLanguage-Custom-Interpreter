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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n\t AST Syntax Tree \n");
        sb.append("Kind: ").append(super.kind).append(" [\n");

        for (Stms stm : body) {
            sb.append("\t  ").append(stm.toString()).append("\n");
        }

        sb.append("]");
        return sb.toString();
    }
}
