package backend.values;

// "type: function | name: string | parameter : String[] | declarationEnv: Environment | body = []"
public class FunctionVal extends RuntimeVal{
    String value = null;

    public FunctionVal(ValueType type, String value){
        super(type);
        this.value = value;
    }
}
