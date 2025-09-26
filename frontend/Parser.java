package frontend;

import frontend.ASTNodeTypes.*;
import frontend._lexer.Token;
import frontend._lexer.TokenTypes;
import frontend._lexer.Lexer;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private ArrayList<Token> tokens = new ArrayList<>();

    //Check If End of line
    private boolean not_eof(){ 
        return this.tokens.get(0).getTokenType() != TokenTypes.EOF;
    }
    //Check current token
    private Token at(){return this.tokens.get(0);}
    //Remove Token
    private Token eat(){
        Token prev = tokens.removeFirst();
        return prev;
    }
    //Check for Expected Token type
    private Token expect(TokenTypes tk, String err){
        // System.out.println(this.tokens.size() + "|" + this.at().getValue() + err);
        Token prev = this.tokens.removeFirst(); 
        if(prev == null|| prev.getValue().isEmpty() || prev.getTokenType() != tk){
            System.out.println("Parser Error:\n"+ " " + err + " " + prev.getValue() + " - Expecting: "+ tk);
            System.exit(0);
        }
        return prev;
    }

    //Produce the Abstract Syntax Tree
    public Program produceAST(String src){
        Lexer lex = new Lexer(src);
        this.tokens = lex.Tokenizer();

        Program program = new Program(new ArrayList<>());
        
        while (this.not_eof()) {
            program.getBody().add(parse_Stms());
        }
        return program;
    }

    //Parse the type of statement
    private Stms parse_Stms(){
        switch (this.at().getTokenType()) {
            case TokenTypes.Let:
            case TokenTypes.Const:
                return this.parse_var_decleration();
            case TokenTypes.Print:
                return this.parse_print_Stms();
            case TokenTypes.IfStm:
                return this.parse_if_Stms();
            // case TokenTypes.Fn:
            //     System.out.println("Implementing Function");
            //     System.exit(0);
            //     break;
            default:
                return this.parse_Expr();
        }
    }
    //Parse Print statement and its rules
    private Stms parse_print_Stms(){
        this.eat();
        this.expect(TokenTypes.OpenParen, "Expect ( Paren");

        List<Expr> args = new ArrayList<>();

        if(this.at().getTokenType() != TokenTypes.CloseParen){
            args.add(this.parse_Expr());
            // System.out.print(this.at().getTokenType());
            while (this.at().getTokenType() == TokenTypes.Comma) {
                this.eat();
                args.add(this.parse_Expr());
            }
        }
        this.expect(TokenTypes.CloseParen, "Expected ')' after print arguments");
        this.expect(TokenTypes.SemiColon, "Missing semicolon after print statement");

        return new PrintStm(args);
    }
    //Parse Var Decleration
    private Stms parse_var_decleration(){
        boolean isConstant = this.eat().getTokenType() == TokenTypes.Const;
        String identifier = this.expect(TokenTypes.Identifier, "Expected Identifier | \"tap\" keyword").getValue();

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
    //Parse Block or statement
    private Stms parse_block_or_stms(){
        if(this.at().getTokenType() == TokenTypes.OpenBrace){
            return parse_block();
        }
        else{
            return parse_Stms();
        }
    }
    //parse block statement
    private Stms parse_block(){
        this.expect(TokenTypes.OpenBrace, "Expect \"{\" to start block");
        ArrayList<Stms> body = new ArrayList<>();
        
        while (this.at().getTokenType() != TokenTypes.CloseBrace) {
            body.add(parse_Stms());
        }
        // System.out.println(this.at().getTokenType());
        this.expect(TokenTypes.CloseBrace, "Expect \"}\" to end block");
        return new BlockStms(body);
    }
    //Parse if statement and its rules
    private Stms parse_if_Stms(){
        this.eat();

        if(this.at().getValue().equals("(")) this.eat();
        Expr condition = this.parse_Expr();
        if(this.at().getValue().equals(")")) this.eat();
        
        Stms thenBranch = parse_block_or_stms();
        
        IfStm root = new IfStm(thenBranch, null, condition);
        IfStm current = root;
        while (this.at().getTokenType() == TokenTypes.ElseIf) {
            this.eat();
            if(this.at().getValue().equals("(")) this.eat();
            Expr elifCond = this.parse_Expr();
            if(this.at().getValue().equals(")")) this.eat();

            Stms elifBranch = parse_block_or_stms();

            IfStm nextIf = new IfStm(thenBranch, elifBranch, elifCond);
            current.setElseBranch(nextIf);
            current = nextIf;
        }

        if(this.at().getTokenType() == TokenTypes.ElseStm){
            this.eat();
            Stms elseBranch = this.parse_block_or_stms();
            current.setElseBranch(elseBranch);
        }
        return root;
    }
    //Parse Expression -> PRECEDENCE LEVEL
    private Expr parse_Expr(){
        return parse_assignment_expr();
    }
    // PRECEDENCE LEVEL
    // assignment -> comparison -> additive -> multiplicative -> parse primary
    private Expr parse_assignment_expr(){
        Expr left = this.parse_comparision_Expr(); 
        if(this.at().getTokenType() == TokenTypes.Equals){
            this.eat();
            Expr value = this.parse_assignment_expr();
            return new AssignmentExpr(left, value);
        }
        
        return left;
    }
    private Expr parse_comparision_Expr(){
        Expr left = this.parse_additive_Expr();
        while (this.at().getTokenType() == TokenTypes.ComparisonOperator) {
            String operator = this.eat().getValue();
            Expr right = this.parse_additive_Expr();
            left = new BinaryExpr(left, right, operator);
        }
        return left;
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
    private Expr parse_primary_Expr(){
        TokenTypes tk =  this.at().getTokenType();
        // System.out.println(tk);
        switch (tk) {
            case TokenTypes.Identifier:
                return new Identifier(this.eat().getValue());
            case TokenTypes.Number:
                return new NumericalLiteral(this.eat().getValue());
            case TokenTypes.StringLiteral:
                return new StringLiteral(this.eat().getValue());
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
