
import java.util.ArrayList;
import java.util.Scanner;

import frontend.ASTNodeTypes.Program;
import frontend.ASTNodeTypes.Stms;

import frontend._lexer.*;
import frontend._parser.Parser;

public class main {

    private static void showProgram(Program prog){
        System.out.println("test");
        for (Stms stm : prog.getBody()) {
            System.out.println(stm);
        }
    }  
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parser parse = new Parser();
        System.out.println("V.1 - Programming language");
        while (true) {
            System.out.print("Enter Expression: ");
            String source = sc.nextLine();
            if(source.equals("exit") || source.isEmpty()) {System.out.println("\n\nExit.."); sc.close(); return;}
            
            Program prog = parse.produceAST(source);
            System.out.println(prog.getBody());
        }
    }    
}
