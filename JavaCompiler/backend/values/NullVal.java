package backend.values;

public class NullVal extends RuntimeVal{
    String value = null;

    public NullVal(String value){
        super(ValueType.Null);
        this.value = value;
    }
}
