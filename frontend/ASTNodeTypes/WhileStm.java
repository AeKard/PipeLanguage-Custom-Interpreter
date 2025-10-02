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
        return "While stms";
    }
}
