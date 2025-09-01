package backend;

import frontend.ASTNodeTypes.*;

import java.util.ArrayList;
import java.util.List;

import backend.values.*;
//TODO: Check for Zerodivisionvalue
public class Interpreter{

    private NullVal MK_Null(){
        return new NullVal(null);
    }

    private RuntimeVal eval_prog(Program program, Environement env){
        RuntimeVal lastEval = this.MK_Null();

        for(Stms stms : program.getBody()){
            lastEval = evalute(stms, env);
        }

        return lastEval;
    }

    private NumberVal eval_numeric_binary_expr(NumberVal lhs, NumberVal rhs, String op){
        Double result;
        if(op.equals("+") ){result = lhs.getValue() + rhs.getValue();}
        else if(op.equals("-")) {result = lhs.getValue() - rhs.getValue();}
        else if(op.equals("/")) {result = lhs.getValue() / rhs.getValue();}
        else if(op.equals("*")) {result = lhs.getValue() * rhs.getValue();}
        else{result = lhs.getValue() / rhs.getValue();}
        // System.out.println(lhs.getValue()+ " "+op + " " +rhs.getValue() + " = "+ Double.toString(result) + "\n\n");
        
        return new NumberVal(Double.toString(result));
    }

    private RuntimeVal eval_binary_expr(BinaryExpr binop, Environement env){
        NumberVal lhs = (NumberVal) evalute(binop.getLeft(), env);
        NumberVal rhs = (NumberVal) evalute(binop.getRight(), env);

        if (lhs.getType().equals(ValueType.Number) && rhs.getType().equals(ValueType.Number)){
            return eval_numeric_binary_expr(lhs, rhs, binop.getValue());
        }
        return this.MK_Null();
    }

    private RuntimeVal eval_identifier(Identifier identifier, Environement env){
        return env.lookup(identifier.getValue());
    }
    private RuntimeVal eval_var_declaration(VarDecleration decleration, Environement env){
        RuntimeVal value = (decleration.getValue() == null) ? this.MK_Null(): this.evalute(decleration.getValue(), env);
        return env.declareVar(decleration.getIdentifier(), value, decleration.getIsContant());
    }
    private RuntimeVal eval_assinment(AssignmentExpr node, Environement env){
        if(!node.getAssigneExpr().getType().equals(NodeType.Identifier)){
            System.out.println("Invalid assignment Expression");
            System.exit(0);
        }
        String varname = node.getAssigneExpr().getValue();
        return env.assignVar(varname, evalute(node.getValueExpr(), env));
    }
    private RuntimeVal eval_print(PrintStm pstm, Environement env) {
    List<String> outputs = new ArrayList<>();

    for (Expr expr : pstm.getValue()) {
        RuntimeVal val = this.evalute(expr, env);
        outputs.add(val.toString());
    }
    System.out.println(String.join(" ", outputs));

    return this.MK_Null();
}
    public RuntimeVal evalute(Stms astNode, Environement env){
        // System.out.println(astNode.getType() + " <- AST Level");
        switch(astNode.getType()){
            case NodeType.StringLiteral:
                StringLiteral sl = (StringLiteral) astNode;
                return new StringVal(sl.getValue());
            case NodeType.NumericalLiteral:
                NumericalLiteral num = (NumericalLiteral) astNode;
                return new NumberVal(num.getValue());
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