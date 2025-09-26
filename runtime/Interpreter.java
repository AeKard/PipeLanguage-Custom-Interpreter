package runtime;

import frontend.ASTNodeTypes.*;

import java.util.ArrayList;
import java.util.List;

import runtime.values.*;
public class Interpreter{
    // Create a Null Class
    private NullVal MK_Null(){return new NullVal("aaNull");}
    // Evaluate the program statement
    private RuntimeVal eval_prog(Program program, Environement env){
        RuntimeVal lastEval = this.MK_Null();

        for(Stms stms : program.getBody()){
            lastEval = evalute(stms, env);
        }

        return lastEval;
    }
    private String formatNumber(double num) {
        if (num == Math.floor(num)) {
            return String.valueOf((long) num);
        }
        return String.valueOf(num);
    }
    private RuntimeVal eval_binary_operator_expr(BinaryExpr binop, Environement env){
        RuntimeVal lhs =  evalute(binop.getLeft(), env);
        RuntimeVal rhs =  evalute(binop.getRight(), env);
        String operator = binop.getValue();
        if(lhs instanceof NumberVal && rhs instanceof NumberVal){
            double l = ((NumberVal) lhs).getAsDouble();
            double r = ((NumberVal) rhs).getAsDouble();
            switch (operator) {
                case "+": return new NumberVal(formatNumber(l + r)); 
                case "-": return new NumberVal(formatNumber(l - r));
                case "*": return new NumberVal(formatNumber(l * r));
                case "/": if(r == 0) {System.out.println("Math Error : Division by Zero error"); System.exit(0);}
                    return new NumberVal(formatNumber(l / r));
                case "%": if(r == 0) {System.out.println("Math Error : Modulo by Zero error"); System.exit(0);}
                    return new NumberVal(formatNumber(l % r));
                
                case ">": return new BooleanVal(l > r);
                case "<": return new BooleanVal(l < r);
                case ">=": return new BooleanVal(l >= r);
                case "<=": return new BooleanVal(l <= r);
                case "==": return new BooleanVal(l == r);
                case "!=": return new BooleanVal(l != r);
                default:
                    break;
            }
            
        }
        if(lhs instanceof StringVal && rhs instanceof StringVal){
            String l = ((StringVal) lhs).getValue();
            String r = ((StringVal) rhs).getValue();

            switch (operator) {
                case "!=":
                    return new BooleanVal(!l.equals(r));
                case "==":
                    System.out.println(l + " == " + r);
                    return new BooleanVal(l.equals(r));
                case "+":
                    return new StringVal(l + r);
                default:
                    System.out.println("Unsupported String operator : " + operator );
                    System.exit(0);
            }
            return new BooleanVal(l.equals(r));
        }

        System.out.println("Unsupported binary operation: " + operator +
        " with " + lhs + " (" + lhs.getClass().getSimpleName() + ")" +
        " and " + rhs + " (" + rhs.getClass().getSimpleName() + ")");
        System.exit(1);
        return this.MK_Null();
    }
    // Enviroment use lookup
    private RuntimeVal eval_identifier(Identifier identifier, Environement env){
        return env.lookup(identifier.getValue());
    }
    // Environment Var declaration
    private RuntimeVal eval_var_declaration(VarDecleration decleration, Environement env){
        RuntimeVal value = (decleration.getValue() == null) ? this.MK_Null(): this.evalute(decleration.getValue(), env);
        return env.declareVar(decleration.getIdentifier(), value, decleration.getIsContant());
    }
    // Environment assigning
    private RuntimeVal eval_assinment(AssignmentExpr node, Environement env){
        if(!node.getAssigneExpr().getType().equals(NodeType.Identifier)){
            System.out.println("Invalid assignment Expression");
            System.exit(0);
        }
        String varname = node.getAssigneExpr().getValue();
        return env.assignVar(varname, evalute(node.getValueExpr(), env));
    }
    
    // Evaluate print statement use join
    private RuntimeVal eval_print(PrintStm pstm, Environement env) {
        List<String> outputs = new ArrayList<>();

        for (Expr expr : pstm.getValue()) {
            RuntimeVal val = this.evalute(expr, env);
            outputs.add((val.toString() == null) ? "Null": val.toString());
        }
        System.out.println(String.join(" ", outputs));

        return this.MK_Null();
    }
    // Evaluate if statement
    private RuntimeVal eval_if_statement(IfStm fstm, Environement env){
        RuntimeVal cond = this.evalute(fstm.getCondition(), env);
        if(!(cond instanceof BooleanVal)){
            System.out.println("Condition in if-statement must be a boolean");
        }
        boolean condition = ((BooleanVal) cond).getValue();
        if (condition){
            return this.evalute(fstm.getThenBranch(), env);
        } else if (fstm.getElseBranch() != null){
            return this.eval_if_statement((IfStm) fstm.getElseBranch(), env);
        }else{
            return this.evalute(fstm.getElseBranch(), env);
        }
    }
    // Evaluate block
    private RuntimeVal eval_block(BlockStms block, Environement env ){
        RuntimeVal last =this.MK_Null();
        for(Stms stmt : block.getBody()){
            last = this.evalute(stmt, env);
        }
        return last;
    }
    // TODO: FIX THE NAMING IN evaluate method
    public RuntimeVal evalute(Stms astNode, Environement env){
        // System.out.println(astNode.getType() + " <- AST Level");
        switch(astNode.getType()){
            case NodeType.StringLiteral:
                StringLiteral sl = (StringLiteral) astNode;
                return new StringVal(sl.getValue().substring(1, sl.getValue().length() - 1));
            case NodeType.NumericalLiteral:
                NumericalLiteral num = (NumericalLiteral) astNode;
                return new NumberVal(num.getValue());
            case NodeType.If:
                IfStm ifNode = (IfStm) astNode;
                return this.eval_if_statement(ifNode, env);
            case NodeType.Identifier:
                Identifier ident = (Identifier) astNode;
                return this.eval_identifier(ident, env);
            case NodeType.AssignmentExpr:
                AssignmentExpr AE = (AssignmentExpr) astNode;
                return this.eval_assinment(AE, env);
            case NodeType.BinaryExpr:
                BinaryExpr expr = (BinaryExpr) astNode;
                return this.eval_binary_operator_expr(expr, env);
            case NodeType.VarDecleration:
                VarDecleration VarD = (VarDecleration) astNode;
                return this.eval_var_declaration(VarD, env);
            case NodeType.Print:
                PrintStm printNode = (PrintStm) astNode;
                return this.eval_print(printNode, env);
            case NodeType.Block:
                BlockStms block = (BlockStms) astNode;
                return this.eval_block(block, env);
            case NodeType.Program:
                Program pg = (Program) astNode;
                return this.eval_prog(pg, env);
            default:
                System.out.println("This AST Node has not been setup for interpretation : " + astNode.getType());
                System.exit(0);
            }
        return this.MK_Null();
    }
}