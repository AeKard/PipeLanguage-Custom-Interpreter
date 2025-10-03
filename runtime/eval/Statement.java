package runtime.eval;

import java.util.ArrayList;
import java.util.List;

import frontend.ASTNodeTypes.*;
import runtime.values.*;
import runtime.Environement;
import runtime.Interpreter;;
public class Statement {
    Interpreter Inter;
    public Statement(Interpreter Inter){
        this.Inter = Inter;
    }
    // Evaluate Identifier
    public RuntimeVal evalIdentifier(Identifier identifier, Environement env){
        return env.lookup(identifier.getValue());
    }
    // Environment Var declaration
    public RuntimeVal evalVarDeclaration(VarDecleration decleration, Environement env){
        RuntimeVal value = (decleration.getValue() == null) ? Inter.MKNULL(): Inter.evaluate(decleration.getValue(), env);
        return env.declareVar(decleration.getIdentifier(), value, decleration.getIsContant());
    }
    // Environment assigning
    public RuntimeVal evalAssinment(AssignmentExpr node, Environement env){
        if(!node.getAssigneExpr().getType().equals(NodeType.Identifier)){
            System.out.println("Invalid assignment Expression");
            System.exit(0);
        }
        String varname = node.getAssigneExpr().getValue();
        return env.assignVar(varname, Inter.evaluate(node.getValueExpr(), env));
    }
    
    // Evaluate print statement use join
    public RuntimeVal evalPrint(PrintStm pstm, Environement env) {
        List<String> outputs = new ArrayList<>();

        for (Expr expr : pstm.getValue()) {
            RuntimeVal val = Inter.evaluate(expr, env);
            outputs.add((val.toString() == null) ? "Null": val.toString());
        }
        System.out.println(String.join(" ", outputs));

        return Inter.MKNULL();
    }
    // Evaluate block
    public RuntimeVal evalBlock(BlockStms block, Environement env) {
        RuntimeVal last = Inter.MKNULL();
        for (Stms stmt : block.getBody()) {
            last = Inter.evaluate(stmt, env);
            if (last instanceof ReturnVal || last instanceof BreakVal || last instanceof ContinueVal) {
                return last;
            }
        }
        return last;
    }
    // Evaluate if statement
    public RuntimeVal evalIfStatement(IfStm fstm, Environement env){
        RuntimeVal cond = Inter.evaluate(fstm.getCondition(), env);
        if(!(cond instanceof BooleanVal)){
            System.out.println("Condition in if-statement must be a boolean");
        }
        
        boolean condition = ((BooleanVal) cond).getValue();
        if (condition){
            return Inter.evaluate(fstm.getThenBranch(), env);
        } 
        else if (fstm.getElseBranch() != null){
            if(fstm.getElseBranch() instanceof IfStm){
                return this.evalIfStatement((IfStm) fstm.getElseBranch(), env);
            }
            else{
                return Inter.evaluate(fstm.getElseBranch(), env);
            }
        }
        return Inter.MKNULL();
    }
    
    public RuntimeVal evalWhile(WhileStm whStms, Environement env){
        RuntimeVal last = Inter.MKNULL();
        while (true) {
            RuntimeVal cond = Inter.evaluate(whStms.getCondition(), env);
            
            if(!(cond instanceof BooleanVal)){
                System.out.println("Conditoin in while must be a boolean value");
                System.exit(0);
            }

            boolean condition = ((BooleanVal) cond).getValue();
            if(!condition){
                break;
            }
            RuntimeVal result = Inter.evaluate(whStms.getBody(), env);

            if(result instanceof BreakVal){
                break;}
            else if(result instanceof ContinueVal){continue;}
            else if(result instanceof ReturnVal){return result;}
            last = result;
        }   
        return last;
    }

    public RuntimeVal evalFnCall(FuncCallStm call, Environement env){
        RuntimeVal fnVal = Inter.evaluate(call.getCallee(), env);

        if (fnVal instanceof FunctionVal) {
            FunctionVal fn = (FunctionVal) fnVal;

            Environement localEnv = new Environement(fn.getEnv());

            for (int i = 0; i < fn.getParam().size(); i++) {
                String param = fn.getParam().get(i);
                RuntimeVal arg = (i < call.getArgs().size())
                        ? Inter.evaluate(call.getArgs().get(i), env)
                        : Inter.MKNULL();
                localEnv.declareVar(param, arg, false);
            }

            RuntimeVal result = Inter.evaluate(fn.getBody(), localEnv);

            if (result instanceof ReturnVal) {
                return ((ReturnVal) result).getValue();
            }
            return Inter.MKNULL();
        }
        System.out.println("Not a function " + fnVal);
        System.exit(0);
        return Inter.MKNULL();
    }
    
    public RuntimeVal evalFunctDecl(FuncDecStm Fn, Environement env){
        FunctionVal fnVal = new FunctionVal(Fn.getFuncName(), Fn.getParameter(), Fn.getBlockStms(), env);
        env.declareVar(Fn.getFuncName(), fnVal, true);
        return fnVal;
    }

    public RuntimeVal evalReturn(ReturnStm ret, Environement env){
        RuntimeVal val = (ret.getValue() != null) ? Inter.evaluate(ret.getValue(), env) : Inter.MKNULL();
        return new ReturnVal(val);
    }
}