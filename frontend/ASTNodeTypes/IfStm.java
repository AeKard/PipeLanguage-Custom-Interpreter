package frontend.ASTNodeTypes;


public class IfStm extends Expr{
    // Use List for then branch
    Expr condition;
    Stms thenBranch;
    Stms elseBranch;

    public IfStm(Stms thenBranch, Stms elseBranch, Expr con){
        super(NodeType.If);
        this.condition = con;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public NodeType getType(){
        return kind;
    }
    public String getValue(){
        return "IfStm does not return value rn";
    }
    public Expr getCondition(){
        return this.condition;
    }
    public Stms getThenBranch(){
        return this.thenBranch;
    }
    public Stms getElseBranch(){
        return this.elseBranch;
    }

    @Override
    public String toString(int indent){
        return "if stms";
    }
}
