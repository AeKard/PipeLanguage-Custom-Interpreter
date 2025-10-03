package frontend.ASTNodeTypes;

import java.util.List;
public class FuncDecStm extends Expr {
    String name;
    List<String> params;
    BlockStms body;

    public FuncDecStm(String name, List<String> params,BlockStms body) {
        super(NodeType.FunctionDec);
        this.name = name;
        this.params = params;
        this.body = body;
    }

    public NodeType getType(){return this.kind;}
    public String getValue(){return this.name;}
    public String getFuncName(){return this.name;}
    public List<String> getParameter(){return this.params;}
    public BlockStms getBlockStms(){return this.body;}

    @Override
    public String toString(int indent){
        String pad = " ".repeat(indent + 2);
        StringBuilder sb = new StringBuilder();

        sb.append("\n" + pad).append("KIND: Function Decleration \n");
        sb.append(pad).append("Function Name: " + this.name + "\n");
        sb.append(pad).append("Params: <").append(String.join(" ",this.params)).append(">\n");
        sb.append(pad).append("value:").append(this.body.toString(indent));

        return sb.toString();
    }
}
