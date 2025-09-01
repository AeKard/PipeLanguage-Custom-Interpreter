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
    public String toString(int indent){
        String pad = " ".repeat(indent);
        return pad + "Assigne: { "+ assigne +"}\n"+pad+"Value:{ "+ value +"}";
    }
}
