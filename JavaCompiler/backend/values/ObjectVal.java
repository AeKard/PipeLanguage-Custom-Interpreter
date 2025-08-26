package backend.values;

import java.util.Map;

public class ObjectVal extends RuntimeVal{
    Map<String, RuntimeVal> properties;

    public ObjectVal(Map<String, RuntimeVal> properties){
        super(ValueType.Object);
        this.properties = properties;
    }
}
