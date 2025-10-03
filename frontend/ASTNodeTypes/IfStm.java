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

    public void setElseBranch(Stms elseBranch) {
        this.elseBranch = elseBranch;
    }
    @Override
    public String toString(int indent){
        String pad = " ".repeat(indent + 2);
        StringBuilder sb = new StringBuilder();

        sb.append("\n" + pad).append("KIND: If\n");
        sb.append(pad).append("condition:").append("\n" + condition.toString(indent + 2) + "\n");
        sb.append(pad).append("body:\n").append(thenBranch.toString(indent));
        if (elseBranch != null) {
            sb.append("\n").append(pad).append("else:\n")
              .append(elseBranch.toString(indent + 2));
        }
        return sb.toString();

    }
}
