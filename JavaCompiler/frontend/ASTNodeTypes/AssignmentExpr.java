package frontend.ASTNodeTypes;

public class AssignmentExpr extends Expr{
    Expr assigne;
    Expr value;

    public AssignmentExpr(Expr assigne, Expr value){
        super(NodeType.AssignmentExpr);
        this.assigne = assigne;
        this.value = value;
    }

    public NodeType getType(){
        return this.kind;
    }
    public Expr getAssigneExpr(){
        return this.assigne;
    }
    public Expr getValueExpr(){
        return this.value;
    }
    public String getValue(){
        return "AssignemntExpr Does not return a String Value";
    }
    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        String pad = "  ".repeat(indent * 2);
        String childPad = "  ".repeat(indent * 4);

        sb.append("\n" + pad).append("kind: AssignmentExpr");

        sb.append("\n").append(childPad).append("    assigne:").append("\n")
          .append(assigne.toString(indent + 4));

        sb.append("\n").append(childPad).append("    value:").append(childPad)
          .append(value.toString(indent + 4));

        return sb.toString();
    }
}
