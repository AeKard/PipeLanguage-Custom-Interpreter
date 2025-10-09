
import java.io.IOException;
import java.nio.file.Files;
// import java.util.Scanner;

import runtime.values.BooleanVal;
import runtime.values.NullVal;

import runtime.Interpreter;
import runtime.Environement;
import frontend.ASTNodeTypes.Program;

import frontend.Parser;

import java.nio.file.Paths;

/*
 * TODO: Use stream for comparison on lexer 
 */
public class Main {

    private static void declaredWord(Environement env){
        env.declareVar("true", new BooleanVal(true), true);
        env.declareVar("false", new BooleanVal(false), true);
        env.declareVar("null", new NullVal(null), true);
    }
    private static void useSourceFile(Interpreter Interp, Parser parse, Environement env, String path){
        try {
            String source = Files.readString(Paths.get(path));

            System.out.println(" \n---- TOKEN TYPES ----\n");
            Program prog = parse.produceAST(source);
            System.out.println(" \n---- AST Tree Representation ----\n");
            System.out.println(prog);
            System.out.println(" \n---- CLI Evaluated ----\n");
            Interp.evaluate(prog, env);
        } catch (IOException e) {
            System.out.println("File Error :" + e);
            System.exit(0);
        }
        return;
    }
    public static void main(String[] args) {
        Interpreter Interp = new Interpreter();
        Parser parse = new Parser();
        Environement env = new Environement(null);
        
        String path = "source.mpipe";
        System.out.println(" === Programming language ===");
        declaredWord(env);
        useSourceFile(Interp, parse, env, path);
    }    
}
