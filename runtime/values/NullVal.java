package backend.values;
// IM DUMB 
public class NullVal extends RuntimeVal{
    String value = null;

    public NullVal(String value){
        super(ValueType.Null);
        this.value = value;
    }
    @Override
    public String toString() {
        return "Null";
    }
}
