
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import runtime.values.BooleanVal;
import runtime.values.NullVal;

import runtime.Interpreter;
import runtime.Environement;
import frontend.ASTNodeTypes.Program;

import frontend.Parser;

import java.nio.file.Paths;

/*
 * TODO: Fix the AST print statements ✔
 * TODO: While loop
 * TODO: create Block of statement ✔
 * ****************
 * TODO: if statement evaluation in runtime ✔
 * TODO: Funtions
 * TODO: Use stream for comparison on lexer 
 */
public class Main {

    private static void declaredWord(Environement env){
        env.declareVar("true", new BooleanVal(true), true);
        env.declareVar("false", new BooleanVal(false), true);
        env.declareVar("null", new NullVal(null), true);
    }
    private static void useSourceFile(Interpreter Interp, Parser parse, Environement env, String path){
        System.out.print("\tFileMode \n\n");
        try {
            String source = Files.readString(Paths.get(path));
            Program prog = parse.produceAST(source);
            System.out.println(prog);
            System.out.println("======Terminal Printing======");
            Interp.evalute(prog, env);
        } catch (IOException e) {
            System.out.println("File Error :" + e);
            System.exit(0);
        }
        return;
    }

    // private static void useTerminal(Interpreter Interp, Parser parse, Environement env, Scanner sc){
    //     while (true) {
    //         System.out.print("\tTerminal Mode\n\n");
    //         String source = sc.nextLine();
    //         if(source.equals("exit") || source.isEmpty()) {System.out.println("\n\nExit.."); sc.close(); return;}
    //         Program prog = parse.produceAST(source);
    //         System.out.println(prog);
    //     }
    // }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Interpreter Interp = new Interpreter();
        Parser parse = new Parser();
        Environement env = new Environement(null);
        
        String path = "source.mpipe";
        System.out.println(" === Programming language ===");
        declaredWord(env);
        useSourceFile(Interp, parse, env, path);
    }    
}
