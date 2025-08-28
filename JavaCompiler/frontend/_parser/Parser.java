package frontend._parser;

import frontend.ASTNodeTypes.*;

import frontend._lexer.Token;
import frontend._lexer.TokenTypes;
import frontend._lexer.Lexer;

import java.util.ArrayList;

//TODO: Add multiplicative if it encounter sudden open paren

public class Parser {
    private ArrayList<Token> tokens = new ArrayList<>();

    private boolean not_eof(){ 
        return this.tokens.get(0).getTokenType() != TokenTypes.EOF;
    } //get type
    private Token at(){return this.tokens.get(0);} //return token
    private Token eat(){
        Token prev = tokens.removeFirst();
        return prev;
    }
    private Token expect(TokenTypes tk, String err){
        Token prev = this.tokens.removeFirst(); 

        if(prev == null|| prev.getValue().isEmpty() || prev.getTokenType() != tk){
            System.out.println("Parser Error:\n"+ " " + err + " " + prev.getValue() + " - Expecting: "+ tk);
            System.exit(0);
        }
        return prev;
    }

    public Program produceAST(String src){
        Lexer lex = new Lexer(src);
        this.tokens = lex.Tokenizer();

        Program program = new Program(new ArrayList<>());
        
        while (this.not_eof()) {
            program.getBody().add(parse_Stms());
        }
        return program;
    }

    private Stms parse_Stms(){
        switch (this.at().getTokenType()) {
            case TokenTypes.Let:
            case TokenTypes.Const:
                return this.parse_var_decleration();
            case TokenTypes.Fn:
                System.out.println("Implementing Function");
                System.exit(0);
                break;
            default:
                return this.parse_Expr();
            }
        return this.parse_Expr();
    }
    private Expr parse_Expr(){
        return parse_additive_Expr();
    }
    
    private Stms parse_var_decleration(){
        boolean isConstant = this.eat().getTokenType() == TokenTypes.Const;
        String identifier = this.expect(TokenTypes.Identifier, "Expected Identifier | const keyword").getValue();

        if(this.at().getTokenType() == TokenTypes.SemiColon){
            this.eat();
            if(isConstant){
                System.out.println("Must assigne value to constant expression. No value provided.");
                System.exit(0);
            }
            return new VarDecleration(false, identifier);
        }

        this.expect(TokenTypes.Equals, "Expect equals token following identifier in var declaration.");

        VarDecleration declaration = new VarDecleration(isConstant, identifier, this.parse_Expr());

        this.expect(TokenTypes.SemiColon, "Variable declaration statment must end with semicolon.");

        return declaration;
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
        System.out.println(tk);
        switch (tk) {
            case TokenTypes.Identifier:
                return new Identifier(this.eat().getValue());
            case TokenTypes.Number:
                return new NumericalLiteral(this.eat().getValue());
            case TokenTypes.OpenParen:
                this.eat();
                Expr value = this.parse_Expr();
                this.expect(TokenTypes.CloseParen, "\"Unexpected token found inside parenthesised expression. Expected closing parenthesis.\",");
                return value;
            default:
                System.out.println("Unexpected token found during parsing! : " + this.at().getValue());
                System.exit(0);
                return new Identifier("");
        }
    }
}
