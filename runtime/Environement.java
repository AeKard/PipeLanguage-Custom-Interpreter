package runtime;

import runtime.values.RuntimeVal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Environement {
    Environement parent;
    Map<String, RuntimeVal> variables;
    Set<String> constants;
    public Environement(Environement env){
        this.parent = env;
        this.variables = new HashMap<>();
        this.constants = new HashSet<>();
    }
    // Check if the variable exist
    public Environement resolve(String varname){
        if(this.variables.containsKey(varname)){
            return this;
        }
        if(this.parent == null){
            System.out.println("Variable does not exit: \""+varname+"\"");
            System.exit(0);
        }
        return this.parent.resolve(varname);
    }
    // declare var and evaluate if constant
    public RuntimeVal declareVar(String varname,  RuntimeVal value, boolean constant){
        if(this.variables.containsKey(varname)){
            System.out.println("Variable : \"" + varname + "\" already decalared");
            System.exit(0);
        };
        this.variables.put(varname, value);
        if(constant){
            this.constants.add(varname);
        }
        return value;
    }
    // Assign value to the var and eval if constant
    public RuntimeVal assignVar(String varname, RuntimeVal value){
        Environement env = this.resolve(varname);
        if (env.constants.contains(varname)) {
            System.out.println("Cannot reassign value because it's constant");
            return env.variables.get(varname); 
        }

        if (env.variables.containsKey(varname)) {
            env.variables.put(varname, value); 
        } else {
            System.out.println("Variable '" + varname + "' not declared");
        }
        return value;
    }
    // Check if that varname exist
    public RuntimeVal lookup(String varname){
        Environement env = this.resolve(varname);
        return env.variables.get(varname);
    }
    
}
