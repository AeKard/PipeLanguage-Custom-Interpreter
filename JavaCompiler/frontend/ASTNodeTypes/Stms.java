package frontend.ASTNodeTypes;

public abstract class Stms {
    final NodeType kind;

    Stms(NodeType kind){
        this.kind = kind;
    }
}
