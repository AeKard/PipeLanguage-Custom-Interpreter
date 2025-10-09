
package frontend._lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.util.regex.Matcher;

public class Lexer {
    String src;
    long pos;

    public Lexer(String src){
        this.src = src;
        pos = 0;
    }

    public ArrayList<Token> Tokenizer(){
        ArrayList<Token> tokens = new ArrayList<>();
        
        String input = src;        
        while(!input.isEmpty()){
            boolean matched = false;
            
            if (input.startsWith("//")) {
                int newlineIndex = input.indexOf("\n");
                if (newlineIndex == -1) {
                    break;
                } else {
                    input = input.substring(newlineIndex + 1);
                    continue; 
                }
            }
            System.out.println(input);
            
            for(TokenRule rule : this.rules()){
                Matcher matcher = rule.pattern.matcher(input);
                
                if(matcher.lookingAt()){
                    String value = matcher.group();
                    tokens.add(new Token(value, rule.type));
                    input = input.substring(matcher.end());
                    matched = true;
                    break;
                }
            }
            if(!matched){
                // IGNORE SPACES FOR NOW OR SOMETHING UNKNOWS IGNORE
                input = input.substring(1);
            }
        }
        
        
        tokens.add(new Token("EOF", TokenTypes.EOF));
        return tokens;
    }
    private List<TokenRule> rules(){
        
        List<TokenRule> rules = Arrays.asList(
            new TokenRule(TokenTypes.Const, "^\\bsealed\\b"),
            new TokenRule(TokenTypes.WhileStm, "^\\bcycle\\b"),
            new TokenRule(TokenTypes.Fn, "^\\bfaucet\\b"),
            new TokenRule(TokenTypes.ReturnStm, "^\\bspill\\b"),
            new TokenRule(TokenTypes.ContinueStm, "\\bflush\\b"),
            new TokenRule(TokenTypes.ReturnStm, "\\bclog\\b"),
            new TokenRule(TokenTypes.LogicOp, "\\b(and|or)\\b"),
            new TokenRule(TokenTypes.Let, "^\\btap\\b"),
            new TokenRule(TokenTypes.IfStm, "^\\bpipe\\b"),
            new TokenRule(TokenTypes.ElseIfStm, "^\\bbranch\\b"),
            new TokenRule(TokenTypes.ElseStm, "^\\bdrain\\b"),
            new TokenRule(TokenTypes.SemiColon, "^;"),
            new TokenRule(TokenTypes.Print, "^\\bflow\\b"),
            new TokenRule(TokenTypes.Number, "^[0-9]+(\\.[0-9]+)?"),
            new TokenRule(TokenTypes.Identifier, "^[A-Za-z_][A-Za-z0-9_]*"),
            new TokenRule(TokenTypes.Comma, "^,"),
            new TokenRule(TokenTypes.StringLiteral, "^\"[^\"]*\""),
            new TokenRule(TokenTypes.StringLiteral, "^'[^']*'"),
            new TokenRule(TokenTypes.BinaryOperator, "^(\\+|\\-|\\*|%|/)"),
            new TokenRule(TokenTypes.ComparisonOperator,"^(!=|<=|>=|>|<|==)"),
            new TokenRule(TokenTypes.OpenParen, "^\\("),
            new TokenRule(TokenTypes.CloseParen, "^\\)"),
            new TokenRule(TokenTypes.Equals, "^="),
            new TokenRule(TokenTypes.OpenBrace,"^\\{"),
            new TokenRule(TokenTypes.CloseBrace,"^\\}")
        );
        
        return rules;
    }
}

