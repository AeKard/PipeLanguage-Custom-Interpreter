package runtime.values;

public class StringVal extends RuntimeVal{
    String value;

    public StringVal(String value){
        super(ValueType.StringVal);
        this.value = value;
    }

    public ValueType getType(){
        return this.type;
    }
    public String getValue(){
        return this.value;
    }
    @Override
    public String toString(){
        return this.value;
    }
}
