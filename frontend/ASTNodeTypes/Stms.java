package frontend.ASTNodeTypes;

public abstract class Stms {
    final NodeType kind;

    Stms(NodeType kind){
        this.kind = kind;
    }

    public abstract String toString(int indent);

    public NodeType getType(){
        return this.kind;
    }

    @Override
    public String toString() {
        return toString(0); 
    }
}
