package runtime;

import frontend.ASTNodeTypes.*;
import runtime.values.*;
import runtime.eval.*;
public class Interpreter{
    Statement st;
    Expression Exp;
    
    public Interpreter(){
        this.st = new Statement(this);
        this.Exp = new Expression(this);
    }
    // Create a Null Class
    public NullVal MKNULL(){return new NullVal("Place holder Null");}

    // Evaluate the program statement
    private RuntimeVal eval_prog(Program program, Environement env){
        RuntimeVal lastEval = MKNULL();
        for(Stms stms : program.getBody()){
            lastEval = evaluate(stms, env);
        }
        return lastEval;
    }

    public RuntimeVal evaluate(Stms astNode, Environement env){
        // System.out.println(astNode.getType() + " <- AST Level");
        switch(astNode.getType()){
            case NodeType.StringLiteral:
                StringLiteral sl = (StringLiteral) astNode;
                return new StringVal(sl.getValue().substring(1, sl.getValue().length() - 1));
            case NodeType.NumericalLiteral:
                return new NumberVal(((NumericalLiteral) astNode).getValue());
            case NodeType.If:
                return st.evalIfStatement((IfStm) astNode, env);
            case NodeType.Identifier:
                return st.evalIdentifier((Identifier) astNode, env);
            case NodeType.AssignmentExpr:
                return st.evalAssinment((AssignmentExpr) astNode, env);
            case NodeType.BinaryExpr:
                return Exp.evalOperation((BinaryExpr) astNode, env);
            case NodeType.VarDecleration:
                return st.evalVarDeclaration((VarDecleration) astNode, env);
            case NodeType.Print:
                return st.evalPrint((PrintStm) astNode, env);
            case NodeType.WhileStm:
                return st.evalWhile((WhileStm) astNode, env);
            case NodeType.BreakStm:
                return new BreakVal();
            case NodeType.ContinueStm:
                return new ContinueVal();
            case NodeType.FunctionDec:
                return st.evalFunctDecl((FuncDecStm) astNode, env);
            case NodeType.FunctionCall:
                return st.evalFnCall((FuncCallStm) astNode, env);
            case NodeType.ReturnStm:
                return st.evalReturn((ReturnStm) astNode, env);
            case NodeType.Block:
                return st.evalBlock((BlockStms) astNode, env);
            case NodeType.Program:
                return this.eval_prog((Program) astNode, env);
            default:
                System.out.println("This AST Node has not been setup for interpretation : " + astNode.getType());
                System.exit(0);
            }
        return MKNULL();
    }
}