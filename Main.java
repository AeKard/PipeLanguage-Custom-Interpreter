
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import runtime.values.BooleanVal;
import runtime.values.NullVal;
import runtime.values.RuntimeVal;
import runtime.Interpreter;
import runtime.Environement;
import frontend.ASTNodeTypes.Program;

import frontend.Parser;

import java.nio.file.Paths;

/*
 * TODO: Fix the AST print statements
 * TODO: While loop
 * TODO: create Block of statement 
 * ****************
 * TODO: FOR LOOP
 * TODO: Funtions
 * TODO: Array
 */
public class Main {

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

    private static void useTerminal(Interpreter Interp, Parser parse, Environement env, Scanner sc){
        while (true) {
            System.out.print("\tTerminal Mode\n\n");
            String source = sc.nextLine();
            if(source.equals("exit") || source.isEmpty()) {System.out.println("\n\nExit.."); sc.close(); return;}
            Program prog = parse.produceAST(source);
            System.out.println(prog);
            // System.out.println(prog);
            // Interp.evalute(prog, env);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Interpreter Interp = new Interpreter();
        Parser parse = new Parser();
        Environement env = new Environement(null);
        
        String path = "source.txt";
        System.out.println(" === Programming language ===");
        env.declareVar("true", new BooleanVal(true), true);
        env.declareVar("false", new BooleanVal(false), true);
        env.declareVar("null", new NullVal(null), true);
        
        
        while (true) {
            System.out.print("[A. File , B.Terminal ]\n> ");
            String type = sc.nextLine();        
            switch (type.toUpperCase()) {
                case "A":
                case "1":
                    useSourceFile(Interp, parse, env, path);
                return;
                case "B":
                case "2":
                    useTerminal(Interp, parse, env, sc);
                    return;
                default:
                    System.out.println("Choose from [A | B]\n==================\n\n");
                    System.out.println("Exit..");
                    break;
            }
        }
    }    
}
