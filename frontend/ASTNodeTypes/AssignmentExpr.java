package frontend.ASTNodeTypes;

public class AssignmentExpr extends Expr{
    Expr assigne;
    Expr value;

    public AssignmentExpr(Expr assigne, Expr value){
        super(NodeType.AssignmentExpr);
        this.assigne = assigne;
        this.value = value;
    }

    public NodeType getType(){return this.kind;}
    public Expr getAssigneExpr(){return this.assigne;}
    public Expr getValueExpr(){return this.value;}
    public String getValue(){return "AssignemntExpr Does not return a String Value";}
    @Override
    public String toString(int indent) {
        String pad = " ".repeat(indent + 2);
        StringBuilder sb = new StringBuilder();

        sb.append("\n" + pad).append("KIND: AssignmentExpr\n");
        sb.append(pad).append("value:\n");

        sb.append(pad).append("  assigne:").append("\n")
          .append(assigne.toString(indent + 2) + "\n");

        sb.append(pad).append("  value:").append("\n")
          .append(value.toString(indent + 4));

        return sb.toString();
    }
}
