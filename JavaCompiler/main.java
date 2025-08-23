
import java.util.Scanner;

import frontend.ASTNodeTypes.Program;

import frontend._parser.Parser;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parser parse = new Parser();
        System.out.println("V.1 - Programming language");
        while (true) {
            System.out.print("\n> ");
            String source = sc.nextLine();
            if(source.equals("exit") || source.isEmpty()) {System.out.println("\n\nExit.."); sc.close(); return;}
            
            Program prog = parse.produceAST(source);
            // System.out.println(prog);
            // System.out.println("Type: Program \nBody: " + prog.getBody() + " }");
        }
    }    
}
