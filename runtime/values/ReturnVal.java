package runtime.values;

public class ReturnVal extends RuntimeVal{
    private final RuntimeVal value;

    public ReturnVal(RuntimeVal value){
        super(ValueType.ReturnVal);
        this.value = value;
    }

    public RuntimeVal getValue(){
        return value;
    }
    @Override
    public String toString(){
        return ""+this.value;
    }
}