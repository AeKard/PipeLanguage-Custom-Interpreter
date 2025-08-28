package backend._environment;

import backend.values.RuntimeVal;

import java.util.HashMap;
import java.util.Map;

// TODO: ADD FOR CONSTANT
public class Environement {
    Environement parent;
    Map<String, RuntimeVal> variables;
    
    public Environement(Environement env){
        this.parent = env;
        this.variables = new HashMap<>();
    }
    public Environement resolve(String varname){
        if(this.variables.containsKey(varname)){
            return this;
        }
        if(this.parent == null){
            System.out.println("Variable does not exit: \""+varname+ "\"");
            System.exit(0);
        }
        return this.parent.resolve(varname);
    }

    public RuntimeVal declareVar(String varname,  RuntimeVal value){
        if(this.variables.containsKey(varname)){
            System.out.println("Variable : \"" + varname + "\"decalared");
            System.exit(0);
        };
        this.variables.put(varname, value);
    
        return value;
    }
    public RuntimeVal assignVar(String varname, RuntimeVal value){
        Environement env = this.resolve(varname);
        env.variables.put(varname, value);
        return value;
    }
    public RuntimeVal lookup(String varname){
        Environement env = this.resolve(varname);
        return env.variables.get(varname);
    }
}
