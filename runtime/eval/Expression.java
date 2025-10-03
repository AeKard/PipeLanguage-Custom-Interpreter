package runtime.eval;

import frontend.ASTNodeTypes.BinaryExpr;

import runtime.Environement;
import runtime.values.*;

import runtime.Interpreter;
public class Expression {
    private Interpreter Inter;

    public Expression(Interpreter Inter){
        this.Inter = Inter;
    }

    private String format_number(double num) {
        if (num == Math.floor(num)) {
            return String.valueOf((long) num);
        }
        return String.valueOf(num);
    }
    public RuntimeVal evalOperation(BinaryExpr binop, Environement env){
        RuntimeVal lhs =  Inter.evaluate(binop.getLeft(), env);
        RuntimeVal rhs =  Inter.evaluate(binop.getRight(), env);
        String operator = binop.getValue();
        if(lhs instanceof NumberVal && rhs instanceof NumberVal){
            double l = ((NumberVal) lhs).getAsDouble();
            double r = ((NumberVal) rhs).getAsDouble();
            switch (operator) {
                case "+": return new NumberVal(format_number(l + r)); 
                case "-": return new NumberVal(format_number(l - r));
                case "*": return new NumberVal(format_number(l * r));
                case "/": if(r == 0) {System.out.println("Math Error : Division by Zero error"); System.exit(0);}
                    return new NumberVal(format_number(l / r));
                case "%": if(r == 0) {System.out.println("Math Error : Modulo by Zero error"); System.exit(0);}
                    return new NumberVal(format_number(l % r));
                
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
        if(lhs instanceof BooleanVal && rhs instanceof BooleanVal){
            boolean l = ((BooleanVal) lhs).getValue();
            boolean r = ((BooleanVal) rhs).getValue();

            switch (operator) {
                case "and": return new BooleanVal(l && r);
                case "or": return new BooleanVal(l || r);
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
        return Inter.MKNULL();
    }
}
