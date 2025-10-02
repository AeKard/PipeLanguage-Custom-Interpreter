package frontend.ASTNodeTypes;

public class ReturnStm extends Stms{
    final Expr value;

    public ReturnStm(Expr value){
        super(NodeType.ReturnStm);
        this.value = value;
    }
    public Expr getValue(){
        return this.value;
    }
    @Override
    public String toString(int indent){
        return "return Stms";
    }
}