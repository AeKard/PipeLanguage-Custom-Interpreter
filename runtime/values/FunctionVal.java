package runtime.values;

import java.util.List;
import frontend.ASTNodeTypes.BlockStms;
import runtime.Environement;

public class FunctionVal extends RuntimeVal{
    String name;
    List<String> param;
    BlockStms body;    
    Environement env;

    public FunctionVal(String name, List<String> param, BlockStms body, Environement env){
        super(ValueType.Function);
        this.name = name;
        this.param = param;
        this.body = body;
        this.env = env;
    }
    public Environement getEnv(){return this.env;}
    public List<String> getParam(){return this.param;}
    public BlockStms getBody(){return this.body;}
    public String getName(){return this.name;}

    @Override
    public String toString(){
        return "Expect open and close paren for function call ()";
    }
}
