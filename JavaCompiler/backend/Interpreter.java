package backend;

import frontend.ASTNodeTypes.*;

import java.util.ArrayList;
import java.util.List;

import backend.values.*;
public class Interpreter{
    // Create a Null Class
    private NullVal MK_Null(){return new NullVal(null);}
    // Evaluate the program statement
    private RuntimeVal eval_prog(Program program, Environement env){
        RuntimeVal lastEval = this.MK_Null();

        for(Stms stms : program.getBody()){
            lastEval = evalute(stms, env);
        }

        return lastEval;
    }
    // Evaluate the left and right expression for Binary TODO: and for String and fix ZeroDivisionValue
    private RuntimeVal eval_binary_expr(BinaryExpr binop, Environement env){
        RuntimeVal lhs =  evalute(binop.getLeft(), env);
        RuntimeVal rhs =  evalute(binop.getRight(), env);
        String operator = binop.getValue();

        if(lhs instanceof NumberVal && rhs instanceof NumberVal){
            double l = ((NumberVal) lhs).getValue();
            double r = ((NumberVal) rhs).getValue();
            switch (operator) {
                case "+": return new NumberVal(Double.toString(l + r)); 
                case "-": return new NumberVal(Double.toString(l - r));
                case "*": return new NumberVal(Double.toString(l * r));
                case "/": return new NumberVal(Double.toString(l / r));
                case "%": return new NumberVal(Double.toString(l % r));
                
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
        // System.out.println("Unsupported binary operation: " + operator +
        // " with " + ((NumberVal) lhs).getType() + " and " + ((NumberVal) rhs).getType());
        //  System.exit(1);
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
            outputs.add(val.toString());
        }
        System.out.println(String.join(" ", outputs));

        return this.MK_Null();
    }

    private RuntimeVal eval_if_statement(IfStm fstm, Environement env){
        RuntimeVal cond = this.evalute(fstm.getCondition(), env);
        if(!(cond instanceof BooleanVal)){
            System.out.println("Condition in if-statement must be a boolean");
        }
        boolean condition = ((BooleanVal) cond).getValue();
        if (condition){
            return this.evalute(fstm.getThenBranch(), env);
        } else if (fstm.getElseBranch() != null){
            return this.evalute(fstm.getElseBranch(), env);
        }
        return this.MK_Null();
    }

    // TODO: FIX THE NAMING IN evaluate method
    public RuntimeVal evalute(Stms astNode, Environement env){
        // System.out.println(astNode.getType() + " <- AST Level");
        switch(astNode.getType()){
            case NodeType.StringLiteral:
                StringLiteral sl = (StringLiteral) astNode;
                return new StringVal(sl.getValue());
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
                return this.eval_binary_expr(expr, env);
            case NodeType.VarDecleration:
                VarDecleration VarD = (VarDecleration) astNode;
                return this.eval_var_declaration(VarD, env);
            case NodeType.Print:
                PrintStm printNode = (PrintStm) astNode;
                return this.eval_print(printNode, env);
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