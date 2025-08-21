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
}
