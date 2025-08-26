package frontend.ASTNodeTypes;

public class VarDecleration extends Stms{
    boolean constant;
    String identifier;
    Expr value = null;

    public VarDecleration(boolean constant, String identifier, Expr value){
        super(NodeType.VarDecleration);
        this.constant = constant;
        this.identifier = identifier;
        this.value = value;
    }
    public VarDecleration(boolean constant, String identifier){
        super(NodeType.VarDecleration);
        this.constant = constant;
        this.identifier = identifier;
    }

    @Override
    public String toString(int indent) {
        return toString(0); 
    }
}
