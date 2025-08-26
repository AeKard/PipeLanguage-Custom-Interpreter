package backend;

import frontend.ASTNodeTypes.*;
import backend.values.*;
//TODO: Check for Zerodivisionvalue
public class Interpreter{

    private NullVal MK_Null(){
        return new NullVal(null);
    }

    private RuntimeVal eval_prog(Program program){
        RuntimeVal lastEval = this.MK_Null();

        for(Stms stms : program.getBody()){
            lastEval = evalute(stms);
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
        System.out.println(lhs.getValue()+ " "+op + " " +rhs.getValue() + " = "+ Double.toString(result) + "\n\n");
        
        return new NumberVal(Double.toString(result));
    }

    private RuntimeVal eval_binary_expr(BinaryExpr binop){
        NumberVal lhs = (NumberVal) evalute(binop.getLeft());
        NumberVal rhs = (NumberVal) evalute(binop.getRight());

        if (lhs.getType().equals(ValueType.Number) && rhs.getType().equals(ValueType.Number)){
            return eval_numeric_binary_expr(lhs, rhs, binop.getValue());
        }
        return this.MK_Null();
    }

    public RuntimeVal evalute(Stms astNode){
        
        switch(astNode.getType()){
            case NodeType.NumericalLiteral:
                NumericalLiteral num = (NumericalLiteral) astNode;
                return new NumberVal(num.getValue());
            case NodeType.BinaryExpr:
                BinaryExpr expr = (BinaryExpr) astNode;
                return this.eval_binary_expr(expr);
            case NodeType.Program:
                Program pg = (Program) astNode;
                return this.eval_prog(pg);
            default:
                System.out.println("This AST Node has not been setup for interpretation : ");
                System.exit(0);
            }
        return this.MK_Null();
    }
}