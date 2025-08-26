package backend.values;

public class BooleanVal extends RuntimeVal {
    String value = null;

    public BooleanVal(String value){
        super(ValueType.Boolean);
        this.value = value;
    }
}
