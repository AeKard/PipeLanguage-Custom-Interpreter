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
        this.value = null;
    }

    public Expr getValue(){
        return this.value;
    }
    public String getIdentifier(){
        return this.identifier;
    }
    public boolean getIsContant(){
        return this.constant;
    }
    @Override
    public String toString(int indent) {
        String pad = " ".repeat(indent);
        return pad + "{ kind: \" "+ this.kind+ "\" , Identifier: \""+ this.identifier +"\"} , Value: " + this.value.getValue();
    }
}
