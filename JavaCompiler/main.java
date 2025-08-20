
import java.util.ArrayList;
import java.util.Scanner;

import frontend.*;
import frontend.TokenTypes;


public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lexer lex;
        System.out.println("V.1 - Programming language");
        while (true) {
            System.out.print("Enter Expression: ");
            String source = sc.nextLine();
            lex = new Lexer(source);

            ArrayList<Token> tokens = lex.Tokenizer();


            for(Token token: tokens){
                System.out.print("{ Value : " + token.getValue() + " |  Type: ");
                switch (token.getTokenType()) {
                    case TokenTypes.Identifier:
                        System.out.print(token.getTokenType() + "}\n");
                        break;
                    case TokenTypes.Number:
                        System.out.print(token.getTokenType() + "}\n");
                        break;
                    case TokenTypes.BinaryOperator:
                        System.out.print(token.getTokenType() + "}\n");
                        break;
                    default:
                        System.out.print("Unknown }\n");
                        break;
                }
            }
            if(source.equals("exit")) {System.out.println("\n\nExit.."); sc.close(); return;}
        }
    }    
}
