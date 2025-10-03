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
        String pad = " ".repeat(indent + 2);
        StringBuilder sb = new StringBuilder();

        sb.append("\n" + pad).append("KIND: VarDecleration\n");
        sb.append(pad).append("Identifier: " + this.identifier + "\n" );
        sb.append(pad).append("Constant:" + this.constant+ "\n");
        sb.append(pad).append("value: ").append((this.value == null)? "Null" : "\n" + this.value.toString(indent + 2));
        return sb.toString();
    }
}
