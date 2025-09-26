package runtime.values;

public class NumberVal extends RuntimeVal{
    String value = null;

    public NumberVal(String value){
        super(ValueType.Number);
        this.value = value;
    }
    public ValueType getType(){
        return this.type;
    }
    public String getValue(){
        return value;
    }
    public double getAsDouble() {
        return Double.parseDouble(value);
    }

    public String toString(){
        return value;
    }
}
