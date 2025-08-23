package frontend._parser;

import frontend.ASTNodeTypes.*;

import frontend._lexer.Token;
import frontend._lexer.TokenTypes;
import frontend._lexer.Lexer;

import java.util.ArrayList;


public class Parser {
    private ArrayList<Token> tokens = new ArrayList<>();

    private boolean not_eof(){ return this.tokens.get(0).getTokenType() != TokenTypes.EOF;} //get type
    private Token at(){return this.tokens.get(0);} //return token
    private Token eat(){
        Token prev = tokens.removeFirst();
        return prev;
    }
    public Program produceAST(String src){
        Lexer lex = new Lexer(src);
        this.tokens = lex.Tokenizer();

        Program program = new Program(new ArrayList<>());
        
        while (not_eof()) {
            program.getBody().add(parse_Stms());
        }
        return program;
    }

    private Stms parse_Stms(){
        return this.parse_Expr();
    }
    private Expr parse_Expr(){
        return parse_additive_Expr();
    }

    private Expr parse_additive_Expr(){
        Expr left = this.parse_multiplicative_Expr();
        while (this.at().getValue().equals("+") || this.at().getValue().equals("-")) {
            String operator = this.eat().getValue();
            Expr right = this.parse_multiplicative_Expr();
            left = new BinaryExpr(left, right, operator);
        }
        return left;
    };

    private Expr parse_multiplicative_Expr(){
        Expr left = this.parse_primary_Expr();
        while (this.at().getValue().equals("/") || this.at().getValue().equals("*") || this.at().getValue().equals("%")) {
            String operator = this.eat().getValue();
            Expr right = this.parse_primary_Expr();
            left = new BinaryExpr(left, right, operator);
        }
        return left;
    };

    //PRECEDENCE LEVEL for now just do the arithmetic

    // parsing expressions
    private Expr parse_primary_Expr(){
        TokenTypes tk =  this.at().getTokenType();
        switch (tk) {
            case TokenTypes.Identifier:
                return new Identifier(this.eat().getValue());
            case TokenTypes.Number:
                return new NumericalLiteral(Float.parseFloat(this.eat().getValue()));
            default:
                System.out.println("Unexpected token found during parsing! : " + this.at().getValue());
                System.exit(0);
                return new Identifier("");
        }
    }
}
