package backend.values;

public class BooleanVal extends RuntimeVal {
    boolean value = true;

    public RuntimeVal MK_BOOL(boolean b){
        return new BooleanVal(b);      
    }

    public BooleanVal(Boolean value){
        super(ValueType.Boolean);
        this.value = value;
    }

    public boolean getValue(){
        return value;
    }
}
