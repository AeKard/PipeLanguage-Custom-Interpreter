package frontend.ASTNodeTypes;

public class WhileStm extends Stms{
    Expr condition;
    Stms body;

    public WhileStm(Stms body, Expr cond){
        super(NodeType.WhileStm);
        this.condition = cond;
        this.body = body;
    }
    public Expr getCondition(){
        return this.condition;
    }

    public Stms getBody(){
        return this.body;
    }
    @Override
    public String toString(int indent){
        String pad = " ".repeat(indent + 2);
        StringBuilder sb = new StringBuilder();

        sb.append("\n" + pad).append("KIND: While \n");
        sb.append(pad).append("Condition: \n" +condition.toString(indent + 2) + "\n");
        sb.append(pad).append("value: ").append(this.body.toString(indent));

        return sb.toString();
    }
}
